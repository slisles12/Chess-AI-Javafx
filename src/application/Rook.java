package application;
import java.util.ArrayList;

/*
 * implementation of the rook
 */
public class Rook extends Piece{
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	private boolean castled; //if this rook has castled yet
	
	/*
	 * constructor
	 */
	public Rook(int positionX, int positionY, char color, boolean castled, Board boardState) {
		//giving base values
		this.positionX = positionX;
		this.positionY = positionY;
		this.boardState = boardState;
		this.color = color;
		this.castled = castled;
	}
	
	//returns the possible movements of the rook
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
		//position evals of rook
		double[][] rookVals = {
           {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
           {-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0},
           {-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0},
           {-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0},
           {-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0},
           {-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0},
           {-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0},
           {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
        };
		return rookVals[positionX][positionY] + 5;
	}
	
	 @Override
	 public String toString() { 
		 return "R";
	 } 
}
