package application;
import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Piece>> boardState = new ArrayList<ArrayList<Piece>>();
	private char turn;
	private boolean checkBlack;
	private boolean checkWhite;
	private boolean checkmateBlack;
	private boolean checkmateWhite;
	
	public Board() {
		//default turn is white
		turn = 'W';
		
		//add the rows
		for (int i = 0; i < 8; i++) {
			boardState.add(new ArrayList<Piece>());
		}

		//row one for white
		boardState.get(0).add(new Rook(0, 0, 'B', false, this));
		boardState.get(0).add(new Knight(1, 0, 'B', this));
		boardState.get(0).add(new Bishop(2, 0, 'B', this));
		boardState.get(0).add(new King(3, 0, 'B', this));
		boardState.get(0).add(new Queen(4, 0, 'B', this));
		boardState.get(0).add(new Bishop(5, 0, 'B', this));
		boardState.get(0).add(new Knight(6, 0, 'B', this));
		boardState.get(0).add(new Rook(7, 0, 'B', false, this));
		
		//row two for white
		for (int i = 0; i < 8; i ++) {
			boardState.get(1).add(new Pawn(i, 1, 'B', false, this));
		}
		
		//no mans land
		for (int i = 2; i < 6; i ++){
			for (int j = 0; j < 8; j++) {
				boardState.get(i).add(new Empty(j, i, this));
			}
		}
		
		//row two for black
		for (int i = 0; i < 8; i ++) {
			boardState.get(6).add(new Pawn(i, 6, 'W', false, this));
		}
		
		//row one for black
		boardState.get(7).add(new Rook(0, 7, 'W', false, this));
		boardState.get(7).add(new Knight(1, 7, 'W', this));
		boardState.get(7).add(new Bishop(2, 7, 'W', this));
		boardState.get(7).add(new King(3, 7, 'W', this));
		boardState.get(7).add(new Queen(4, 7, 'W', this));
		boardState.get(7).add(new Bishop(5, 7, 'W', this));
		boardState.get(7).add(new Knight(6, 7, 'W', this));
		boardState.get(7).add(new Rook(7, 7, 'W', false, this));
		
	}
	
	public Board(Board oldBoard) {
		
		//set the new board
		for (int i = 0; i < 8; i++) {
			boardState.add(new ArrayList<Piece>());
			for (int j = 0; j < 8; j++) {

				//recreating the pieces
				if (oldBoard.boardState.get(i).get(j).toString() == "P"){
					this.boardState.get(i).add(new Pawn(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), oldBoard.boardState.get(i).get(j).moved, this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "Q"){
					this.boardState.get(i).add(new Queen(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "R"){
					this.boardState.get(i).add(new Rook(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), oldBoard.boardState.get(i).get(j).castled, this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "K"){
					this.boardState.get(i).add(new Knight(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "S"){
					this.boardState.get(i).add(new King(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "B"){
					this.boardState.get(i).add(new Bishop(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), oldBoard.boardState.get(i).get(j).getColor(), this));
				}
				else if (oldBoard.boardState.get(i).get(j).toString() == "O"){
					this.boardState.get(i).add(new Empty(oldBoard.boardState.get(i).get(j).getPositionX(), oldBoard.boardState.get(i).get(j).getPositionY(), this));
				}
			}
		}
		
		this.turn = oldBoard.turn;
		
		//check for checks
		checkBlack = getCheckBlack();
		checkWhite = getCheckWhite();
		
		
	}
	
	/*
	 * returns the boardState
	 */
	public ArrayList<ArrayList<Piece>> getState() {
		return boardState;
	}
	
	/*
	 * return the turn
	 */
	public char getTurn() {
		return turn;
	}
	
	/*
	 * returns if white is in check
	 */
	public boolean getCheckBlack() {
		//re init it
		checkBlack = false;
		
		//go through
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece temp = boardState.get(i).get(j);
				if (temp.toString()!= "O" && temp.getColor() == 'W') {
					if (boardState.get(i).get(j).moves().size() != 0) {	
						for (ArrayList<Integer> moveSet : temp.moves()) {
							//if a piece can hit the king
							if (boardState.get(moveSet.get(0)).get(moveSet.get(1)).toString() == "S") {
								checkBlack = true;
							}
						}
					}
				}
			}
		}
		return checkBlack;
	}
	
	/*
	 * returns if white is in check
	 */
	public boolean getCheckWhite() {
		//re init it
		checkWhite = false;
		
		//go through
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece temp = boardState.get(i).get(j);
				if (temp.toString()!= "O" && temp.getColor() == 'B') {
					if (boardState.get(i).get(j).moves().size() != 0) {	
						for (ArrayList<Integer> moveSet : temp.moves()) {
							//if a piece can hit the king
							if (boardState.get(moveSet.get(0)).get(moveSet.get(1)).toString() == "S") {
								checkWhite = true;
							}
						}
					}
				}
			}
		}
		return checkWhite;
	}
	
	/*
	 * method for determining if black has been checkmated
	 */
	public boolean getCheckmateBlack() {
		
		//re start
		checkmateBlack = false;

		//if we are checked
		if (getCheckBlack()) {
			
			//go through
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (boardState.get(i).get(j).toString() == "B") {
						
						Piece piece = boardState.get(i).get(j);
						
						Board temp = new Board(this);
						
						//for each possible move
						for (ArrayList<Integer> moveSet : piece.moves()) {
							
							int[] target = new int[2];
							int[] current = new int[2];
							
							target[0] = moveSet.get(0);
							target[1] = moveSet.get(1);
							
							current[0] = i;
							current[1] = j;
							
							//attempt the swap
							if (temp.doSwap(target, current)) {
								checkmateBlack = true;
								return checkmateBlack;
							}

							
						}
					}
				}
			}
			
			checkmateBlack = false;
			return checkmateBlack;
		}

		return checkmateBlack;
	}
	
	/*
	 * method for determining if white has been checkmated
	 */
	public boolean getCheckmateWhite() {
		
		
		//re start
		checkmateWhite = false;

		//if we are checked
		if (getCheckWhite()) {
			
			//go through
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (boardState.get(i).get(j).toString() == "W") {
						
						Piece piece = boardState.get(i).get(j);
						
						Board temp = new Board(this);
						
						//for each possible move
						for (ArrayList<Integer> moveSet : piece.moves()) {
							
							int[] target = new int[2];
							int[] current = new int[2];
							
							target[0] = moveSet.get(0);
							target[1] = moveSet.get(1);
							
							current[0] = i;
							current[1] = j;
							
							//attempt the swap
							if (temp.doSwap(target, current)) {
								System.out.println(temp.toString());
								checkmateWhite = true;
								return checkmateWhite;
							}

							
						}
					}
				}
			}
			
			checkmateWhite = false;
			return checkmateWhite;
		}
		
		return checkmateWhite;
	}
	
	/*
	 * toString for the board
	 * mostly used for testing purposes
	 */
	@Override
	public String toString() { 
		 
		 String temp = "";
		 
		 //go through each piece
		 for (int i = 0; i < 8; i++) {
			 for (int j = 0; j < 8; j++) {
				 temp += " " + boardState.get(i).get(j) + " ";
			 }
			 temp += "\n";
		 }
		 
		 //return the string
		 return temp;
	}

	/*
	 * swaps the pieces
	 * most important method in board
	 */
	public boolean doSwap(int[] target, int[] buttonPressed) {

		Piece currentPiece = boardState.get(buttonPressed[0]).get(buttonPressed[1]);
		Piece lost = boardState.get(target[0]).get(target[1]);
		
		ArrayList<ArrayList<Integer>> possibleMoves = currentPiece.moves();
		
		//if we have moves
		if (possibleMoves != null) {
			//for each move
			for (ArrayList<Integer> move : possibleMoves) {
				//if we can move
				if ((int) move.get(0) == target[0] && (int) move.get(1) == target[1]) {
					
					//if turn is white
					if (turn == 'W') {

						//swap
						boardState.get(buttonPressed[0]).set(buttonPressed[1], new Empty(buttonPressed[0], buttonPressed[1], this));
						currentPiece.setDimension(target[1], target[0]);
						
						//recreating the pieces
						if (currentPiece.toString() == "P"){
							this.boardState.get(target[0]).set(target[1], new Pawn(target[0], target[1], currentPiece.getColor(), currentPiece.moved, this));
						}
						else if (currentPiece.toString() == "Q"){
							this.boardState.get(target[0]).set(target[1], new Queen(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "R"){
							this.boardState.get(target[0]).set(target[1], new Rook(target[0], target[1], currentPiece.getColor(), currentPiece.castled, this));
						}
						else if (currentPiece.toString() == "K"){
							this.boardState.get(target[0]).set(target[1], new Knight(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "S"){
							this.boardState.get(target[0]).set(target[1], new King(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "B"){
							this.boardState.get(target[0]).set(target[1], new Bishop(target[0], target[1], currentPiece.getColor(), this));
						}

						
						if (getCheckWhite() == true) {
							boardState.get(buttonPressed[0]).set(buttonPressed[1], currentPiece);
							currentPiece.setDimension(buttonPressed[1], buttonPressed[0]);
							boardState.get(target[0]).set(target[1], lost);
							return false;
						}
						else {
							//swap tha turns
							if (turn == 'B') {
								turn = 'W';
							}
							else {
								turn = 'B';
							}
							
							//return true
							return true;
						}

					}	
					//if turn is black
					else {
						
						//swap
						boardState.get(buttonPressed[0]).set(buttonPressed[1], new Empty(buttonPressed[0], buttonPressed[1], this));
						currentPiece.setDimension(target[1], target[0]);
						
						//recreating the pieces
						if (currentPiece.toString() == "P"){
							this.boardState.get(target[0]).set(target[1], new Pawn(target[0], target[1], currentPiece.getColor(), currentPiece.moved, this));
						}
						else if (currentPiece.toString() == "Q"){
							this.boardState.get(target[0]).set(target[1], new Queen(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "R"){
							this.boardState.get(target[0]).set(target[1], new Rook(target[0], target[1], currentPiece.getColor(), currentPiece.castled, this));
						}
						else if (currentPiece.toString() == "K"){
							this.boardState.get(target[0]).set(target[1], new Knight(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "S"){
							this.boardState.get(target[0]).set(target[1], new King(target[0], target[1], currentPiece.getColor(), this));
						}
						else if (currentPiece.toString() == "B"){
							this.boardState.get(target[0]).set(target[1], new Bishop(target[0], target[1], currentPiece.getColor(), this));
						}

						if (getCheckBlack() == true) {
							boardState.get(buttonPressed[0]).set(buttonPressed[1], currentPiece);
							currentPiece.setDimension(buttonPressed[1], buttonPressed[0]);
							boardState.get(target[0]).set(target[1], lost);
							return false;
						}
						else {
							//swap tha turns
							if (turn == 'B') {
								turn = 'W';
							}
							else {
								turn = 'B';
							}
							
							//return true
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * gives the value of the board for a specific color
	 */
	public double boardValue(char color) {
		
		//reset values
		double blackValue = 0.0;
		double whiteValue = 0.0;
		
		//go through
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardState.get(i).get(j).toString() != "O") {
					Piece piece = boardState.get(i).get(j);
					if (piece.getColor() == 'W') {
						whiteValue += piece.getValue();
					}
					else if (piece.getColor() == 'B') {
						blackValue += piece.getValue();
					}
				}
			}
		}
		
		//add for checks and checkmates
		if (getCheckmateBlack()) {
			blackValue -= 1000;
		}
		else if (getCheckBlack()) {
			blackValue -= 100;
		}
		if (getCheckmateWhite()) {
			whiteValue -= 1000;
		}
		else if (getCheckWhite()) {
			whiteValue -= 100;
		}
		
		
		//value based on color
		if (color == 'W') {
			return whiteValue - blackValue;
		}
		else {
			return blackValue - whiteValue;
		}

		
	}
	
	/*
	 * changes the turn
	 */
	public void changeTurn() {
		if (this.turn == 'B') {
			this.turn = 'W';
		}
		else {
			this.turn = 'B';
		}
	}
	
	/*
	 * method to return the pieces left for a given color
	 */
	public ArrayList<Piece> getPiecesLeft (char color){
		
		//holds pieces
		ArrayList<Piece> boardPieces = new ArrayList<Piece>();
		
		//go through
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardState.get(i).get(j).toString() != "O") {
					Piece piece = boardState.get(i).get(j);
					if (piece.getColor() == color) {
						boardPieces.add(piece);
					}
				}
			}
		}
		
		//return list
		return boardPieces;
	}
	
	/*
	 * method that returns list of possible boards for a given piece
	 */
	public ArrayList<Board> nextBoards (Piece piece){
		
		ArrayList<Board> nextBoards = new ArrayList<Board>();
		
		//go through
		for (ArrayList<Integer> moveSet : piece.moves()) {
				Board temp = new Board(this);
			
				int[] target = new int[2];
				int[] current = new int[2];
				
				target[0] = moveSet.get(0);
				target[1] = moveSet.get(1);
				
				current[0] = piece.getPositionY();
				current[1] = piece.getPositionX();
				
				//if turn is white
				if (turn == 'W') {

					//swap
					temp.boardState.get(current[0]).set(current[1], new Empty(current[0], current[1], temp));
					
					//recreating the pieces
					if (piece.toString() == "P"){
						temp.boardState.get(target[0]).set(target[1], new Pawn(moveSet.get(1), moveSet.get(0), piece.getColor(), piece.moved, temp));
					}
					else if (piece.toString() == "Q"){
						temp.boardState.get(target[0]).set(target[1], new Queen(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "R"){
						temp.boardState.get(target[0]).set(target[1], new Rook(moveSet.get(1), moveSet.get(0), piece.getColor(), piece.castled, temp));
					}
					else if (piece.toString() == "K"){
						temp.boardState.get(target[0]).set(target[1], new Knight(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "S"){
						temp.boardState.get(target[0]).set(target[1], new King(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "B"){
						temp.boardState.get(target[0]).set(target[1], new Bishop(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					
					if (!temp.getCheckWhite()) {
						temp.changeTurn();
						nextBoards.add(temp);
					}


				}	
				//if turn is black
				else {
					
					//swap
					temp.boardState.get(current[0]).set(current[1], new Empty(current[0], current[1], temp));

					//recreating the pieces
					if (piece.toString() == "P"){
						temp.boardState.get(target[0]).set(target[1], new Pawn(moveSet.get(1), moveSet.get(0), piece.getColor(), piece.moved, temp));
					}
					else if (piece.toString() == "Q"){
						temp.boardState.get(target[0]).set(target[1], new Queen(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "R"){
						temp.boardState.get(target[0]).set(target[1], new Rook(moveSet.get(1), moveSet.get(0), piece.getColor(), piece.castled, temp));
					}
					else if (piece.toString() == "K"){
						temp.boardState.get(target[0]).set(target[1], new Knight(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "S"){
						temp.boardState.get(target[0]).set(target[1], new King(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					else if (piece.toString() == "B"){
						temp.boardState.get(target[0]).set(target[1], new Bishop(moveSet.get(1), moveSet.get(0), piece.getColor(), temp));
					}
					
					if (!temp.getCheckBlack()) {
						temp.changeTurn();
						nextBoards.add(temp);
					}

				}
		}
		
		//return lists of boards
		return nextBoards;
	}
	
	
	
}

