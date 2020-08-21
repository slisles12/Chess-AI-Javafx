package application;
import java.util.ArrayList;

/*
 * Implementation of the pawn
 */
public class Pawn extends Piece {
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	private boolean moved; //if we have moved this pawn yet and can no longer do the two move up
	
	/*
	 * constructor
	 */
	public Pawn(int positionX, int positionY, char color, boolean moved, Board boardState) {
		//giving base values
		this.positionX = positionX;
		this.positionY = positionY;
		this.boardState = boardState;
		this.color = color;
		this.moved = moved;
	}

	/*
	 * sets dimensions of piece
	 */
	@Override
	public void setDimension(int i, int j) {

		this.positionX = i;
		this.positionY = j;
		this.moved = true;
		
		
	} 
	
	//returns the possible movements of the pawn
	public ArrayList<ArrayList<Integer>> moves() {
		nextMoves = new ArrayList<ArrayList<Integer>>();
		
		//if we are white
		if (color == 'B') {
			//if not at edge
			if (positionY + 1 <= 7 && boardState.getState().get(positionY + 1).get(positionX).getColor() == 'O') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX);
			}
			//if we can still double move
			if (positionY + 2 <= 7 && moved == false && boardState.getState().get(positionY + 2).get(positionX).getColor() == 'O') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY + 2);
				nextMoves.get(nextMoves.size() - 1).add(positionX);
			}
			//taking piece to the right
			if (positionY + 1 <= 7 && positionX + 1 <= 7 && boardState.getState().get(positionY + 1).get(positionX + 1).getColor() == 'W') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
			}
			//taking piece to the left
			if (positionY + 1 <= 7 && positionX - 1 >= 0 && boardState.getState().get(positionY + 1).get(positionX - 1).getColor() == 'W') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
			}
		}
		//if we are black
		else {
			//if not at edge
			if (positionY - 1 >= 0 && boardState.getState().get(positionY - 1).get(positionX).getColor() == 'O') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX);
			}
			//if we can still double
			if (positionY - 2 >= 0 && moved == false && boardState.getState().get(positionY - 2).get(positionX).getColor() == 'O') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY - 2);
				nextMoves.get(nextMoves.size() - 1).add(positionX);
			}
			//taking piece to the right
			if (positionY - 1 >= 0 && positionX + 1 <= 7 && boardState.getState().get(positionY - 1).get(positionX + 1).getColor() == 'B') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
			}
			//taking piece to the left
			if (positionY - 1 >= 0 && positionX - 1 >= 0 && boardState.getState().get(positionY - 1).get(positionX - 1).getColor() == 'B') {
				nextMoves.add(new ArrayList<Integer>());
				nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
				nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
			}
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
		//position evals of pawns
		double[][] pawnValsWhite = {
            {0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0},
            {5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0},
            {1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0},
            {0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5},
            {0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0},
            {0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5},
            {0.5,  1.0, 1.0,  -2.0, -2.0,  1.0,  1.0,  0.5},
            {0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0}
        };
		
		//if we are a white bishop
		if (color == 'W') {
			//return the position
			return pawnValsWhite[positionX][positionY] + 3;
		}
		//if we are a black bishop
		else {
			//make black bishop evals
		    double pawnValsBlack[][] = new double[8][8];
		    
		    //black bishop evals are just reversed bishop evals
		    for(int i = 8-1; i >= 0; i--) {
		        for(int j = 8-1; j >= 0; j--) {
		        	pawnValsBlack[8-1-i][8-1-j] = pawnValsWhite[i][j];
		        }
		    }
		    
		    //return the bishop evals for a black piece
		    return pawnValsBlack[positionX][positionY] + 3;
		    
		}
		
	}
	
	 @Override
	 public String toString() { 
		 return "P";
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