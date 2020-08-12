package application;
import java.util.ArrayList;

/*
 * implementation of the queen piece
 */
public class Queen extends Piece {
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	
	/*
	 * constructor
	 */
	public Queen(int positionX, int positionY, char color, Board boardState) {
		//giving base values
		this.positionX = positionX;
		this.positionY = positionY;
		this.boardState = boardState;
		this.color = color;
	}
	
	//returns the possible movements of the queen
	public ArrayList<ArrayList<Integer>> moves() {
		nextMoves = new ArrayList<ArrayList<Integer>>();

		if (!(positionY + 1 >= 7)) {
			//for vertical moves up the board
			if (boardState.getState().get(positionY + 1).get(positionX).toString().equals("O")) {
				for (int i = positionY; i <= 7; i++) {
					//if we have not hit a piece
					if (boardState.getState().get(i).get(positionX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(i);
						nextMoves.get(nextMoves.size() - 1).add(positionX);
					}
					//else we have hit a piece
					else if (boardState.getState().get(i).get(positionX).getColor() != boardState.getState().get(positionY).get(positionX).getColor()){
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(i);
						nextMoves.get(nextMoves.size() - 1).add(positionX);
						break;
					}
				}
			}
		}
		
		if (!(positionY - 1 <= 0)) {
			if (boardState.getState().get(positionY - 1).get(positionX).toString().equals("O")) {
				//for vertical moves down the board
				for (int i = positionY; i >= 0; i--) {
					//if we have not hit a piece
					if (boardState.getState().get(i).get(positionX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(i);
						nextMoves.get(nextMoves.size() - 1).add(positionX);
					}
					//else if we have hit a piece
					else if (boardState.getState().get(i).get(positionX).getColor() != boardState.getState().get(positionY).get(positionX).getColor()){
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(i);
						nextMoves.get(nextMoves.size() - 1).add(positionX);
						break;
					}
				}
			}
		}

		if (!(positionX + 1 >= 7)) {
			if (boardState.getState().get(positionY).get(positionX + 1).toString().equals("O")) {
				//for horizontal moves to the right of the board
				for (int i = positionX; i <= 7; i++) {
					//if we have not hit a piece
					if (boardState.getState().get(positionY).get(i).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(positionY);
						nextMoves.get(nextMoves.size() - 1).add(i);						
					}
					//else if we have hit a piece
					else if (boardState.getState().get(positionY).get(i).getColor() != boardState.getState().get(positionY).get(positionX).getColor()){
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(positionY);
						nextMoves.get(nextMoves.size() - 1).add(i);
						break;
					}
				}
			}
		}
		
		if (!(positionX - 1 <= 0)) {
			if (boardState.getState().get(positionY).get(positionX - 1).toString().equals("O")) {
				//for horizontal moves left of the board
				for (int i = positionX; i >= 0; i--) {
					//if we have not hit a piece
					if (boardState.getState().get(positionY).get(i).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(positionY);
						nextMoves.get(nextMoves.size() - 1).add(i);
					}
					//else if we have hit a piece
					else if (boardState.getState().get(positionY).get(i).getColor() != boardState.getState().get(positionY).get(positionX).getColor()){
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(positionY);
						nextMoves.get(nextMoves.size() - 1).add(i);
						break;
					}
				}
			}
		}
		
		//set our counters
				int tempX = positionX;
				int tempY = positionY;

				
				while (tempX < 7 && tempY < 7) {
			
					//change counters
					tempX++;
					tempY++;
				
					//if we have not hit a piece
					if (boardState.getState().get(tempY).get(tempX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
					}
					//else we have hit a piece
					else if (boardState.getState().get(tempY).get(tempX).getColor() != this.color) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
						break;
					}
					else {
						break;
					}
				}
				
				//reset counters
				tempX = positionX;
				tempY = positionY;
				
				while (tempX > 0 && tempY > 0) {
					
					//change counters
					tempX--;
					tempY--;
					
					//if we have not hit a piece
					if (boardState.getState().get(tempY).get(tempX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
					}
					//else we have hit a piece
					else if (boardState.getState().get(tempY).get(tempX).getColor() != this.color) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
						break;
					}
					else {
						break;
					}

				}
				
				//reset counters
				tempX = positionX;
				tempY = positionY;
				
				while (tempX < 7 && tempY > 0) {
					
					//change counters
					tempX++;
					tempY--;
					
					//if we have not hit a piece
					if (boardState.getState().get(tempY).get(tempX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
					}
					//else we have hit a piece
					else if (boardState.getState().get(tempY).get(tempX).getColor() != this.color) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
						break;
					}
					else {
						break;
					}
			
				}
				
				//reset counters
				tempX = positionX;
				tempY = positionY;
				
				while (tempX > 0 && tempY < 7) {
					
					//change counters
					tempX--;
					tempY++;
					
					//if we have not hit a piece
					if (boardState.getState().get(tempY).get(tempX).toString().equals("O")) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get(nextMoves.size() - 1).add(tempX);
					}
					//else we have hit a piece
					else if (boardState.getState().get(tempY).get(tempX).getColor() != this.color) {
						nextMoves.add(new ArrayList<Integer>());
						nextMoves.get(nextMoves.size() - 1).add(tempY);
						nextMoves.get (nextMoves.size() - 1).add(tempX);
						break;
					}
					else {
						break;
					}
				}

				//return the positions of possible moves
				return nextMoves;

	}
	
	/*
	 * returns the color
	 */
	public char getColor() {
		return color;
	}
	
	/*
	 * returning the value of the piece based on its position
	 */
	public double getValue() {
		//position evals of queen
		double[][] queenEvals =   {
           { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0},
           { -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
           { -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0},
           { -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
           {  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
           { -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0},
           { -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0},
           { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}
        };
		
		return queenEvals[positionX][positionY] + 3;
	}
	
	 @Override
	 public String toString() { 
		 return "Q";
	 } 
	
}
