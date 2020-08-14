package application;
import java.util.ArrayList;

/*
 * Abstract class for pieces
 */
public abstract class Piece {
	/*
	 * Method for returning basic possible movements of the pieces 
	 */
	public abstract ArrayList<ArrayList<Integer>> moves();
	public abstract char getColor();
	public abstract void setDimension(int i, int j);
	
}
