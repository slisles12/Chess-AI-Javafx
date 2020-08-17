package application;
import java.util.ArrayList;

/*
 * Implementation of the pawn
 */
public class Empty extends Piece {
	private int positionX; //the position x of the piece
	private int positionY; //the position y of the piece
	private char color = 'O'; //no color
	
	/*
	 * constructor
	 */
	public Empty(int positionX, int positionY, Board boardState) {
		//giving base values
		this.positionX = positionX;
		this.positionY = positionY;
		this.boardState = boardState;
	}
	
	/*
	 * sets dimensions of piece
	 */
	@Override
	public void setDimension(int i, int j) {

		this.positionX = i;
		this.positionY = j;
		
	} 

	//returns the possible movements of the pawn
	public ArrayList<ArrayList<Integer>> moves() {
		return null;
	}
	
	/*
	 * returns the color
	 */
	public char getColor() {
		return color;
	}
	
	 @Override
	 public String toString() { 
		 return "O";
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
