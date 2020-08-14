package application;
import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<Piece>> boardState = new ArrayList<ArrayList<Piece>>();
	private char turn;
	
	public Board() {
		//default turn is white
		turn = 'W';
		
		//add the rows
		for (int i = 0; i < 8; i++) {
			boardState.add(new ArrayList<Piece>());
		}

		//row one for white
		boardState.get(0).add(new Rook(0, 0, 'W', false, this));
		boardState.get(0).add(new Knight(1, 0, 'W', this));
		boardState.get(0).add(new Bishop(2, 0, 'W', this));
		boardState.get(0).add(new King(3, 0, 'W', this));
		boardState.get(0).add(new Queen(4, 0, 'W', this));
		boardState.get(0).add(new Bishop(5, 0, 'W', this));
		boardState.get(0).add(new Knight(6, 0, 'W', this));
		boardState.get(0).add(new Rook(7, 0, 'W', false, this));
		
		//row two for white
		for (int i = 0; i < 8; i ++) {
			boardState.get(1).add(new Pawn(i, 1, 'W', false, this));
		}
		
		//no mans land
		for (int i = 2; i < 6; i ++){
			for (int j = 0; j < 8; j++) {
				boardState.get(i).add(new Empty(j, i, this));
			}
		}
		
		//row two for black
		for (int i = 0; i < 8; i ++) {
			boardState.get(6).add(new Pawn(i, 6, 'B', false, this));
		}
		
		//row one for black
		boardState.get(7).add(new Rook(0, 7, 'B', false, this));
		boardState.get(7).add(new Knight(1, 7, 'B', this));
		boardState.get(7).add(new Bishop(2, 7, 'B', this));
		boardState.get(7).add(new King(3, 7, 'B', this));
		boardState.get(7).add(new Queen(4, 7, 'B', this));
		boardState.get(7).add(new Bishop(5, 7, 'B', this));
		boardState.get(7).add(new Knight(6, 7, 'B', this));
		boardState.get(7).add(new Rook(7, 7, 'B', false, this));
		
	}
	
	public Board(Board oldBoard) {
		
		//swap the turns
		if (oldBoard.getTurn() == 'W') {
			this.turn = 'B';
		}
		else {
			this.turn = 'W';
		}
		
		//set the new board
		this.boardState = oldBoard.boardState;
		
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

	public boolean doSwap(int[] target, int[] buttonPressed) {

		Piece currentPiece = boardState.get(buttonPressed[0]).get(buttonPressed[1]);
		ArrayList<ArrayList<Integer>> possibleMoves = currentPiece.moves();
		
		if (possibleMoves != null) {
			for (ArrayList<Integer> move : possibleMoves) {
				for (Integer val: move) {
					System.out.print(val);
					System.out.print(" ");
				}
				System.out.println();
				if ((int) move.get(0) == target[0] && (int) move.get(1) == target[1]) {
					boardState.get(buttonPressed[0]).set(buttonPressed[1], new Empty(buttonPressed[0], buttonPressed[1], this));
					currentPiece.setDimension(target[1], target[0]);
					boardState.get(target[0]).set(target[1], currentPiece);
					
					if (turn == 'W') {
						turn = 'B';
					}
					else {
						turn = 'W';
					}
					
					System.out.println();
					
					return true;
				}
			}
		}
		return false;
	} 
	
}
