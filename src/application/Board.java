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
		
		//swap the turns
		if (oldBoard.getTurn() == 'B') {
			this.turn = 'W';
		}
		else {
			this.turn = 'B';
		}
		
		//set the new board
		for (int i = 0; i < 8; i++) {
			boardState.add(new ArrayList<Piece>());
			for (int j = 0; j < 8; j++) {
				
				Piece temp = oldBoard.boardState.get(i).get(j);
				
				//recreating the pieces
				if (temp.toString() == "P"){
					this.boardState.get(i).add(new Pawn(temp.positionX, temp.positionY, temp.getColor(), temp.moved, this));
				}
				else if (temp.toString() == "Q"){
					this.boardState.get(i).add(new Queen(temp.positionX, temp.positionY, temp.getColor(), this));
				}
				else if (temp.toString() == "R"){
					this.boardState.get(i).add(new Rook(temp.positionX, temp.positionY, temp.getColor(), temp.castled, this));
				}
				else if (temp.toString() == "K"){
					this.boardState.get(i).add(new Knight(temp.positionX, temp.positionY, temp.getColor(), this));
				}
				else if (temp.toString() == "S"){
					this.boardState.get(i).add(new King(temp.positionX, temp.positionY, temp.getColor(), this));
				}
				else if (temp.toString() == "B"){
					this.boardState.get(i).add(new Bishop(temp.positionX, temp.positionY, temp.getColor(), this));
				}
				else if (temp.toString() == "O"){
					this.boardState.get(i).add(new Empty(temp.positionX, temp.positionY, this));
				}
			}
		}
		
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
		
		//list of checks
		ArrayList<Boolean> listOfChecks = new ArrayList<Boolean>();
		
		//if we are checked
		if (getCheckBlack()) {
			
			//go through
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (boardState.get(i).get(j).toString() == "S" && boardState.get(i).get(j).getColor() == 'B') {
						
						Piece blackKing = boardState.get(i).get(j);
						
						Board temp = new Board(this);
						
						//for each possible move
						for (ArrayList<Integer> moveSet : blackKing.moves()) {
							
							int[] target = new int[2];
							int[] current = new int[2];
							
							target[0] = moveSet.get(0);
							target[1] = moveSet.get(1);
							
							current[0] = i;
							current[1] = j;
							
							//attempt the swap
							temp.doSwap(target, current);

							listOfChecks.add(temp.getCheckBlack());
						}
					}
				}
			}
		}
		
		if (listOfChecks.size() > 0) {
			for (boolean temp : listOfChecks) {
				if (temp == false) {
					return checkmateBlack;
				}
			}
			
			checkmateBlack = true;
			
			return checkmateBlack;
		}
		else {
			return checkmateBlack;
		}
	}
	
	/*
	 * method for determining if white has been checkmated
	 */
	public boolean getCheckmateWhite() {
		
		//re start
		checkmateWhite = false;
		
		//list of checks
		ArrayList<Boolean> listOfChecks = new ArrayList<Boolean>();
		
		//if we are checked
		if (getCheckWhite()) {
			
			//go through
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (boardState.get(i).get(j).toString() == "S" && boardState.get(i).get(j).getColor() == 'W') {
						
						Piece whiteKing = boardState.get(i).get(j);
						
						Board temp = new Board(this);
						
						//for each possible move
						for (ArrayList<Integer> moveSet : whiteKing.moves()) {
							
							int[] target = new int[2];
							int[] current = new int[2];
							
							target[0] = moveSet.get(0);
							target[1] = moveSet.get(1);
							
							current[0] = i;
							current[1] = j;
							
							//attempt the swap
							temp.doSwap(target, current);

							listOfChecks.add(temp.getCheckWhite());
						}
					}
				}
			}
		}
		
		//go through each check
		
		
		if (listOfChecks.size() > 0) {
			for (boolean temp : listOfChecks) {
				if (temp == false) {
					return checkmateWhite;
				}
			}
			
			checkmateWhite = true;
			
			return checkmateWhite;
		}
		else {
			return checkmateWhite;
		}

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
						boardState.get(target[0]).set(target[1], currentPiece);

						
						if (getCheckWhite() == true) {
							boardState.get(buttonPressed[0]).set(buttonPressed[1], currentPiece);
							currentPiece.setDimension(buttonPressed[1], buttonPressed[0]);
							boardState.get(target[0]).set(target[1], lost);
							return false;
						}

					}	
					//if turn is black
					else {
						
						//swap
						boardState.get(buttonPressed[0]).set(buttonPressed[1], new Empty(buttonPressed[0], buttonPressed[1], this));
						currentPiece.setDimension(target[1], target[0]);
						boardState.get(target[0]).set(target[1], currentPiece);

						
						if (getCheckBlack() == true) {
							boardState.get(buttonPressed[0]).set(buttonPressed[1], currentPiece);
							currentPiece.setDimension(buttonPressed[1], buttonPressed[0]);
							boardState.get(target[0]).set(target[1], lost);
							return false;
						}
					}
				}	
			}

					
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

		//not possible to swap
		return false;
	} 

}
