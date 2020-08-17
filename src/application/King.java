package application;
import java.util.ArrayList;
/*
 * implementation of the king piece
 */
public class King extends Piece {
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	
	/*
	 * constructor
	 */
	public King(int positionX, int positionY, char color, Board boardState) {
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
	
	//returns the possible movements of the king
	public ArrayList<ArrayList<Integer>> moves() {
		nextMoves = new ArrayList<ArrayList<Integer>>();	

		//move up
		if (positionY + 1 <= 7 && boardState.getState().get(positionY + 1).get(positionX).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX);
		}
		
		//move down
		if (positionY - 1 >= 0 && boardState.getState().get(positionY - 1).get(positionX).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX);
		}
		
		//move right
		if (positionX + 1 <= 7 && boardState.getState().get(positionY).get(positionX + 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
		}
		
		//more left
		if (positionX - 1 >= 0 && boardState.getState().get(positionY).get(positionX - 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
		}
		
		//move corner
		if (positionY + 1 <= 7 && positionX + 1 <= 7 && boardState.getState().get(positionY + 1).get(positionX + 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
		}
		
		//move corner
		if (positionY - 1 >= 0 && positionX + 1 <= 7 && boardState.getState().get(positionY - 1).get(positionX + 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
		}
		
		//move corner
		if (positionY + 1 <= 7 && positionX - 1 >= 0 && boardState.getState().get(positionY + 1).get(positionX - 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
		}
		
		//more corner
		if (positionY - 1 >= 0 && positionX - 1 >= 0 && boardState.getState().get(positionY - 1).get(positionX - 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
		}



		//return moves
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
		double[][] kingEvals = {
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            { -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0},
            { -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0},
            {  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 },
            {  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 }
        };

		//if we are a white king
		if (color == 'W') {
			//return the position
			return kingEvals[positionX][positionY] + 3;
		}
		//if we are a black bishop
		else {
			//make black king evals
		    double kingEvalsBlack[][] = new double[8][8];
		    
		    //king bishop evals are just reversed king evals
		    for(int i = 8-1; i >= 0; i--) {
		        for(int j = 8-1; j >= 0; j--) {
		            kingEvalsBlack[8-1-i][8-1-j] = kingEvals[i][j];
		        }
		    }
		    
		    //return the king evals for a black piece
		    return kingEvalsBlack[positionX][positionY] + 3;
		    
		}
	
	}
	
	 @Override
	 public String toString() { 
		 return "S";
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
