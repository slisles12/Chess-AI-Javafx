package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Player {
	
	/**
	 * Try to place a piece in the grid, and then return the new board if it's successful
	 * 
	 * @param Board board is the current board
	 * @param char Player is the player whose turn it is
	 * @return Board the chosen move based on the alpha beta results
	 */
	public Board AlphaBeta(Board board, char player) {
		
		//get the pieces possible based on the turn
		ArrayList<Piece> piecesPossible = board.getPiecesLeft(board.getTurn());
	    
		//hashmap to keep track of results
		HashMap<Double, Board> listOfBoards = new HashMap<Double, Board>();             

		//for each piece possible
		for (int i = 0; i < piecesPossible.size(); i++) {
	    	//for each move that each piece can make
	        for (Board b: board.nextBoards(piecesPossible.get(i))) {
	        	//value is the result from running alpha beta
	        	double value = MaxValue(b, 1, player, -1000.0, 1000.0);
	        	
	        	//add the value to the hashmap
	        	listOfBoards.put(value, b);
	        	 
	        }
		}
		
		//maxKey = the maximum from the hashmap
		Double maxKey = Collections.max(listOfBoards.keySet());
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(listOfBoards.get(maxKey).getState().get(i).get(j).getPositionX() + "" + listOfBoards.get(maxKey).getState().get(i).get(j).getPositionY() + " ");
			}
			System.out.println();
		}

		
		return new Board(listOfBoards.get(maxKey));
	
	}
	
	
	/**
	 * Run the max part of minimax with alpha beta pruning
	 * @param Board board is the current board we are on
	 * @param int depth is the depth of the search so far
	 * @param char player is the player we are searching for
	 * @param double alpha is the current alpha value
	 * @param double beta is the current beta value
	 * @return double the value determined by the search
	 */
	public double MaxValue(Board board, int depth, char player, Double alpha, Double beta) {
		
		//if we have reached the end of our leaves
		if (depth == 2 || board.getCheckmateWhite() == true || board.getCheckmateBlack() == true) {
			//return score
			return board.boardValue(player);
		}
		else {
			//get pieces possible
			ArrayList<Piece> piecesPossible = board.getPiecesLeft(board.getTurn());
	         
			ArrayList<Board> listOfBoards = new ArrayList<Board>();                       
	
			//for each piece possible
			for (int i = 0; i < piecesPossible.size(); i++) {
			    //for each move that each piece can make
			    for (Board b: board.nextBoards(piecesPossible.get(i))) {
			    	listOfBoards.add(b);
			    }
			}     
				
			//value starts at -10000
			Double value = -10000.0; 
			
			//for every move we can make
			for (Board aBoard: listOfBoards) {
				//assign minValue to temp
				Double temp = MinValue(aBoard, depth+1, player, alpha, beta);
				
				//if temp is greater than our saved
				if (temp > value) {
					//new value is temp
					value = temp;
				}
				
				//if value is greater than or equal to beta
				if (value >= beta) {
					//prune
					return value;
				}
				
				//if value is greater than alpha
				if (value > alpha) {
					//alpha is now value
					alpha = value;
				}
			}
			 
			return value;
	
		}
	}
	
	/**
	 * Run the max part of minimax with alpha beta pruning
	 * @param Board board is the current board we are on
	 * @param int depth is the depth of the search so far
	 * @param char player is the player we are searching for
	 * @param double alpha is the current alpha value
	 * @param double beta is the current beta value
	 * @return int the value determined by the search
	 */
	public double MinValue(Board board, int depth, char player, Double alpha, Double beta) {
	
		//if we have reached the end of our leaves
		if (depth == 2 || board.getCheckmateWhite() == true || board.getCheckmateBlack() == true) {
			//System.out.println(board.toString());
			
			//return score
			return board.boardValue(player);
		}
		//if not leaf
		else {
			//get pieces possible
			ArrayList<Piece> piecesPossible = board.getPiecesLeft(board.getTurn());
	         
			ArrayList<Board> listOfBoards = new ArrayList<Board>();                       
	
			//for each piece possible
			for (int i = 0; i < piecesPossible.size(); i++) {
			    //for each move that each piece can make
			    for (Board b: board.nextBoards(piecesPossible.get(i))) {
			    	listOfBoards.add(b);
			    }
			}     
				
			//value starts at 10000
			Double value = 10000.0; 
			 
			//for every board we can do
			for (Board aBoard: listOfBoards) {
				 
				//temp is the max value 
				Double temp = MaxValue(aBoard, depth+1, player, alpha, beta);
					
				//if temp is less than value
				if (temp < value) {
					//value is temp
					value = temp;
				}
					
				//if value is less than or equal to alpha
				if (value <= alpha) {
					//prune
					return value;
				}
					
				//if value is less than beta
				if (value < beta) {
					//beta is value
					beta = value;
				}
			}
			
			//return value
			return value;
		}
	}
}