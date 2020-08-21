package application;
import java.util.ArrayList;

/*
 * Abstract class for pieces
 */
public abstract class Piece {
	public boolean moved;
	public int positionX;
	public int positionY;
	public Board boardState;
	public char color;
	public boolean castled;
	/*
	 * Method for returning basic possible movements of the pieces 
	 */
	public abstract ArrayList<ArrayList<Integer>> moves();
	public abstract char getColor();
	public abstract void setDimension(int i, int j);
	public abstract int getPositionX();
	public abstract int getPositionY();
	protected abstract double getValue();
}
