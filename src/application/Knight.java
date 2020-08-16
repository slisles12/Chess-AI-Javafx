package application;
import java.util.ArrayList;

/*
 * implements the king piece
 */
public class Knight extends Piece{
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private Board boardState; //the current board of the game
	private char color; //color of the piece
	private ArrayList<ArrayList<Integer>> nextMoves; //holds next possible movements
	
	/*
	 * constructor
	 */
	public Knight(int positionX, int positionY, char color, Board boardState) {
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
	
	
	
	//returns the possible movements of the knight
	public ArrayList<ArrayList<Integer>> moves() {
		
		nextMoves = new ArrayList<ArrayList<Integer>>();
		
		//up and right first L
		if (positionY + 1 <= 7 && positionX + 2 <= 7 && boardState.getState().get(positionY + 1).get(positionX + 2).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 2);
		}
		
		//up and right second L
		if (positionY + 2 <= 7 && positionX + 1 <= 7 && boardState.getState().get(positionY + 2).get(positionX + 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 2);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
		}
		
		//up and left first L
		if (positionY + 1 <= 7 && positionX - 2 >= 0 && boardState.getState().get(positionY + 1).get(positionX - 2).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 2);
		}
		
		//up and left second L
		if (positionY + 2 <= 7 && positionX - 1 >= 0 && boardState.getState().get(positionY + 2).get(positionX - 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY + 2);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
		}
		
		//down and right first L
		if (positionY - 1 >= 0 && positionX + 2 <= 7 && boardState.getState().get(positionY - 1).get(positionX + 2).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 2);
		}
		
		//down and right second L
		if (positionY - 2 >= 0 && positionX + 1 <= 7 && boardState.getState().get(positionY - 2).get(positionX + 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 2);
			nextMoves.get(nextMoves.size() - 1).add(positionX + 1);
		}
		
		//down and left first L
		if (positionY - 1 >= 0 && positionX - 2 >= 0 && boardState.getState().get(positionY - 1).get(positionX - 2).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 1);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 2);
		}
		
		//down and left second L
		if (positionY - 2 >= 0 && positionX - 1 >= 0 && boardState.getState().get(positionY - 2).get(positionX - 1).getColor() != this.color) {
			nextMoves.add(new ArrayList<Integer>());
			nextMoves.get(nextMoves.size() - 1).add(positionY - 2);
			nextMoves.get(nextMoves.size() - 1).add(positionX - 1);
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
		//position evals of knight
		double[][] knightEvals =  {
               {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
               {-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0},
               {-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0},
               {-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0},
               {-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0},
               {-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0},
               {-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0},
               {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
           };
		return knightEvals[positionX][positionY] + 3;
	}
	
	
	 @Override
	 public String toString() { 
		 return "K";
	 } 
	
}
