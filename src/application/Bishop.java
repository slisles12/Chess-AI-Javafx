package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * implements the bishop piece
 */
public class Bishop extends Piece{
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	
	/*
	 * constructor
	 */
	public Bishop(int positionX, int positionY, char color, Board boardState) {
		//giving base values
		this.positionX = positionX;
		this.positionY = positionY;
		this.boardState = boardState;
		this.color = color;
	}
	
	/*
	 * sets dimensions of piece
	 */
	@Override
	public void setDimension(int i, int j) {

		this.positionX = i;
		this.positionY = j;
		
	} 
	
	//returns the possible movements of the rook
	public ArrayList<ArrayList<Integer>> moves() {
		nextMoves = new ArrayList<ArrayList<Integer>>();
		
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
		//position evals of bishop
		double[][] bishopEvals = {
                { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
                { -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
                { -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0},
                { -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0},
                { -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0},
                { -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0},
                { -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0},
                { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}
		};
		
		//if we are a white bishop
		if (color == 'W') {
			//return the position
			return bishopEvals[positionX][positionY] + 3;
		}
		//if we are a black bishop
		else {
			//make black bishop evals
		    double bishopEvalsBlack[][] = new double[8][8];
		    
		    //black bishop evals are just reversed bishop evals
		    for(int i = 8-1; i >= 0; i--) {
		        for(int j = 8-1; j >= 0; j--) {
		            bishopEvalsBlack[8-1-i][8-1-j] = bishopEvals[i][j];
		        }
		    }
		    
		    //return the bishop evals for a black piece
		    return bishopEvalsBlack[positionX][positionY] + 3;
		    
		}
	
	}
	
	 @Override
	 public String toString() { 
		 return "B";
	 } 
	 
	 /*
	  * returns position x
	  */
	 public int getPositionX() {
		 return positionX;
	 }
	 
	 /*
	  * returns position y
	  */
	 public int getPositionY() {
		 return positionY;
	 }

}
