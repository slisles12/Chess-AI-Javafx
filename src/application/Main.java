package application;
	
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Main extends Application {
	
    static boolean readyPlay = false; //if we are ready to play the game
    static boolean gameOver = false;
    static boolean playerBlack = true;
    static int height = 600; //height of GUI
    static int width = 600; //width of GUI
    static int[] buttonPressed = new int[2]; //array of button pressed
    
	@Override
	public void start(Stage primaryStage) {
		
		Board b = new Board();
		ArrayList<Board> board = new ArrayList<Board>();
		board.add(b);
		
		try {
			 	Label label = new Label("Chess!");
		        label.setFont(new Font("Arial", 60));
		        label.setLayoutX(200);

		        //button
		        Button button1 = new Button("Play Without AI");
		        Button button2 = new Button("Play With AI");
		        
		        //font
		        button1.setStyle("-fx-font-size:20;");
		        button2.setStyle("-fx-font-size:20;");

		        //dimensions
		        button1.setPrefHeight(40);
		        button1.setPrefWidth(220);
		        button2.setPrefHeight(40);
		        button2.setPrefWidth(220);

		        //pane
		        Pane root = new Pane();
		        
		        //position
		        button1.setLayoutX(200);
		        button1.setLayoutY(250);
		        button2.setLayoutX(200);
		        button2.setLayoutY(300);



		        //add things to root
		        root.getChildren().add(button1);
		        root.getChildren().add(button2);
		        root.getChildren().add(label);
		     
		        //create scene
		        Scene scene = new Scene(root,600,600);

		        //add to and show stages
		        primaryStage.setScene(scene);
		        primaryStage.show();
		        
		        //create event for play button
		        button1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
			            readyPlay = true;
		            }
		        });
		        
		      //create event for play button
		        button2.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
			            readyPlay = true;
			            playerBlack = false;
		            }
		        });
		        
                //checking if the human pressed a button
                buttonPressed[0] = -1;
                buttonPressed[1] = -1;

		        
		        //animation
		        new AnimationTimer() {
		            @Override
		            public void handle(long now) {
		            
		            	 Player AI = new Player();
		            	 
		            	 if (board.get(0).getTurn() == 'B' && playerBlack == false) {
		            		 board.set(0, AI.AlphaBeta(board.get(0), board.get(0).getTurn()));
		            		
		            	 }
		            	
		            	 if (board.get(0).getCheckmateBlack() || board.get(0).getCheckmateWhite()) {
		            		 gameOver = true;
		            	 }
		            	 
		            	 if (gameOver == true && readyPlay == true) {
		            		Label label = new Label("GAME OVER!");
		     		        label.setFont(new Font("Arial", 60));
		     		        label.setLayoutX(140);

		     		        //button
		     		        Button button1 = new Button("back to home");

		     		        //font
		     		        button1.setStyle("-fx-font-size:20;");

		     		        //dimensions
		     		        button1.setPrefHeight(40);
		     		        button1.setPrefWidth(220);

		     		        //pane
		     		        Pane root = new Pane();
		     		        
		     		        //position
		     		        button1.setLayoutX(200);
		     		        button1.setLayoutY(250);


		     		        //add things to root
		     		        root.getChildren().add(button1);
		     		        root.getChildren().add(label);
		     		     
		     		        //create scene
		     		        Scene scene = new Scene(root,600,600);

		     		        //add to and show stages
		     		        primaryStage.setScene(scene);
		     		        primaryStage.show();
		     		        
		     		        //create event for play button
		     		        button1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		     		            @Override public void handle(ActionEvent e) {
		     		            	readyPlay = false;
		     			            Main.launch(Main.class);
		     		            }
		     		        });
		            	 }
		            	
		            	 //if we are ready to play
		            	 if (readyPlay == true && gameOver != true) {
		            		 //set title
		                	 primaryStage.setTitle("Game is being played");
		                	 
		                	 //make new grid for buttons
		                     GridPane grid = new GridPane(); 
		                     
		                     //for every piece
	                         for (int i = 0; i < 8; i++) {
	                             for (int j = 0; j < 8; j++) {

	                                 //Creating a rectangle object         
	                                 Rectangle rectangle = new Rectangle();
	                                        
	                                 //set rectangle
	                                 rectangle.setHeight(height/8);
	                                 rectangle.setWidth(width/8);
	                                    
	                                 //add to the grid
	                                 GridPane.setRowIndex(rectangle, j);
	                                 GridPane.setColumnIndex(rectangle, i);
	                                 

	                                 //if i,j mod 2 are 0 then make it mahogany
	                                 if (i % 2 == 0 && j % 2 == 0) {
	                                     rectangle.setFill(Color.web("5B443E"));
	                                 }
	                                 // if both i and j mod 2 is not 0 make it mahogany 
	                                 else if (i % 2 != 0 && j % 2 != 0) {
	                                     rectangle.setFill(Color.web("5B443E"));
	                                 }
	                                 //else make it sycamore
	                                 else {
	                                     rectangle.setFill(Color.web("#8f7d44"));
	                                 }
	                               //set action 
	                               rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {

	            						 @Override
	            						 public void handle(MouseEvent event) {
	            							 //if the button has been pressed
	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	            								 //array to send
	            								 int[] target = new int[2];
	            								 
	            								 //add values to array
	            								 target[0] = GridPane.getRowIndex(rectangle);
	            								 target[1] = GridPane.getColumnIndex(rectangle);
	            								 
	            								 //attempt the swap
	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	            									 buttonPressed[0] = -1;
	            									 buttonPressed[1] = -1;
	             								 
	            								 }
	            								 
	            								 
             						             System.out.println("Check for white is " + board.get(0).getCheckWhite());
             						             System.out.println("Check for black is " + board.get(0).getCheckBlack());
             						             System.out.println("Check mate for white is " + board.get(0).getCheckmateWhite());
             						             System.out.println("Check mate for black is " + board.get(0).getCheckmateBlack());
             						             System.out.println("Score for white is " + board.get(0).boardValue('W'));
            						             System.out.println("Score for black is " + board.get(0).boardValue('B'));
             						             System.out.println();
	            								 
	            								 start();
	     
	            							 }
	            						 }
	            					 });
	                                 grid.getChildren().add(rectangle);

	                             }
	                             
	                         }
		                     
		                     //for every piece
	                         for (int i = 0; i < 8; i++) {
	                             for (int j = 0; j < 8; j++) {

	                            	 //if black
	                            	 if (board.get(0).getState().get(j).get(i).getColor() == 'B') {
	                            		 
	                            		 //if pawn
	                            		 if (board.get(0).getState().get(j).get(i).toString() == "P") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_pawn.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                								 }
	                							 }); 
	                						 }		 
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if rook
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "R") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_rook.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 }); 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if knight
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "K") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_knight.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 }); 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if king
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "S") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_king.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });
	                						 }		
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if queen
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "Q") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_queen.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 }); 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if bishop
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "B") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("black_bishop.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'B' && playerBlack == true) {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });	 	      	                                
	                						 }		
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            	 }	 
	                           
	                            	 else if (board.get(0).getState().get(j).get(i).getColor() == 'W') {
	                            		 
	                            		 //if pawn
	                            		 if (board.get(0).getState().get(j).get(i).toString() == "P") {
	                            			 
	                            			 //image
	                            			 Image img = new Image("white_pawn.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });		 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if rook
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "R") {
	                            			 
	                            			//image
	                            			 Image img = new Image("white_rook.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 }); 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
                						 
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if knight
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "K") {
	                            			 
	                            			//image
	                            			 Image img = new Image("white_knight.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if king
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "S") {
	                            			 
	                            			//image
	                            			 Image img = new Image("white_king.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });	 
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if queen
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "Q") {
	                            			 
	                            			//image
	                            			 Image img = new Image("white_queen.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                						 
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            		 
	                            		 //if bishop
	                            		 else if (board.get(0).getState().get(j).get(i).toString() == "B") {
	                            			 
	                            			//image
	                            			 Image img = new Image("white_bishop.png");
	                            		     ImageView image = new ImageView(img);
	                            		     image.setFitHeight(80);
	                            		     image.setFitWidth(58);
	                            		     image.setPreserveRatio(true);
	                            			 
	                            			 //new button
	                            			 Button bt = new Button();
	                                         bt.setPrefSize(80, 58);
	                                         
	                                         bt.setGraphic(image);
	                                         bt.setStyle("-fx-background-color:transparent");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.get(0).getTurn() == 'W') {
	                							 bt.setOnAction(new EventHandler<ActionEvent>() {
	                								 @Override public void handle(ActionEvent e) {
	                									 //set values of button pressed
	                									 buttonPressed[0] = GridPane.getRowIndex(bt);
	                									 buttonPressed[1] = GridPane.getColumnIndex(bt);
	                                                
	                								 }
	                							 });
	                						 }
	                						 else {
	                							//set action 
	 	      	                                bt.setOnMouseClicked(new EventHandler<MouseEvent>() {

	 	      	            						 @Override
	 	      	            						 public void handle(MouseEvent event) {
	 	      	            							 //if the button has been pressed
	 	      	            							 if (buttonPressed[0] != -1 && buttonPressed[1] != -1) {
	 	      	            								 //array to send
	 	      	            								 int[] target = new int[2];
	 	      	            								 
	 	      	            								 //add values to array
	 	      	            								 target[0] = GridPane.getRowIndex(bt);
	 	      	            								 target[1] = GridPane.getColumnIndex(bt);
	 	      	            								 
	 	      	            								 
	 	      	            								 
	 	      	            								 //attempt the swap
	 	      	            								 if (board.get(0).doSwap(target, buttonPressed)) {
	 	      	            									 buttonPressed[0] = -1;
	 	      	            									 buttonPressed[1] = -1;
	 	      	            								 }
	 	      	            								 
	 	      	            								 start();
	 	      	     
	 	      	            							 }
	 	      	            						 }
	 	      	            					 }); 
	                						 }
	                                         //add to the grid
	                                         grid.add(bt, i, j);
	                            		 }
	                            	 }
	                             }
	                             
	                         }
		                     
		                     //add grid to the scene
		                     primaryStage.setScene(new Scene(grid,height,width));
		                     //show the scene
		                     primaryStage.show();
		                     
			            	 stop();
			            	 
		            	 }
		            	
		            }
		        }.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
