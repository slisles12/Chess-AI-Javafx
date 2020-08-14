package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    static int height = 600; //height of GUI
    static int width = 600; //width of GUI
    static int[] buttonPressed = new int[2]; //array of button pressed
    
	@Override
	public void start(Stage primaryStage) {
		
		Board board = new Board();
		
		try {
			 	Label label = new Label("Chess!");
		        label.setFont(new Font("Arial", 60));
		        label.setLayoutX(200);

		        //button
		        Button button1 = new Button("Play");

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
			            readyPlay = true;
				        
				        System.out.println(readyPlay);
		            }
		        });
		        
                //checking if the human pressed a button
                buttonPressed[0] = -1;
                buttonPressed[1] = -1;

		        
		        //animation
		        new AnimationTimer() {
		            @Override
		            public void handle(long now) {
		            	
		            	 //if we are ready to play
		            	 if (readyPlay == true) {
		            		 //set title
		                	 primaryStage.setTitle("Game is being played");
		                	 
		                 	//final for action handlers
		                 	final Board otherBoard = board;
		                 	
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
	            								 if (board.doSwap(target, buttonPressed)) {
	            									 buttonPressed[0] = -1;
	            									 buttonPressed[1] = -1;
	            								 }
	            								 
	            								 start();
	     
	            							 }
	            						 }
	            					 });
	                                 grid.getChildren().add(rectangle);

	                             }
	                             
	                         }
                             
		                     //add grid to the scene
		                     primaryStage.setScene(new Scene(grid,height,width));
		                     //show the scene
		                     primaryStage.show();
		                     
		                     //for every piece
	                         for (int i = 0; i < 8; i++) {
	                             for (int j = 0; j < 8; j++) {

	                            	 //if black
	                            	 if (board.getState().get(j).get(i).getColor() == 'B') {
	                            		 
	                            		 //if pawn
	                            		 if (board.getState().get(j).get(i).toString() == "P") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("P");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "R") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("R");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "K") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("K");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");

	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "S") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("S");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "Q") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("Q");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "B") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("B");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: Black;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'B') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                           
	                            	 else if (board.getState().get(j).get(i).getColor() == 'W') {
	                            		 
	                            		 //if pawn
	                            		 if (board.getState().get(j).get(i).toString() == "P") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("P");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "R") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("R");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "K") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("K");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "S") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("S");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "Q") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("Q");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
	                            		 else if (board.getState().get(j).get(i).toString() == "B") {
	                            			 
	                            			 //new button
	                            			 Button bt = new Button("B");
	                                         bt.setShape(new Circle(width/8));
	                                         bt.setPrefSize(80, 65);
	                                         bt.setStyle("-fx-background-color: White;" +  "-fx-font-size:25;");
	                                         
	                                         //if it is white's turn and white is human
	                						 if (board.getTurn() == 'W') {
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
	 	      	            								 if (board.doSwap(target, buttonPressed)) {
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
		                     
			            	 stop();
			            	 
		            	 }
		            	
		            }
		        }.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Board testing = new Board();
		

		System.out.println(testing.toString());
		
		//should be R
		System.out.println(testing.getState().get(0).get(0).toString());	
		System.out.println(testing.getState().get(0).get(0).moves());
		
		//should be K
		System.out.println(testing.getState().get(0).get(1).toString());	
		System.out.println(testing.getState().get(0).get(1).moves());
		
		//should be B
		System.out.println(testing.getState().get(0).get(2).toString());	
		System.out.println(testing.getState().get(0).get(2).moves());
		
		//should be S
		System.out.println(testing.getState().get(0).get(3).toString());	
		System.out.println(testing.getState().get(0).get(3).moves());
		
		//should be Q
		System.out.println(testing.getState().get(0).get(4).toString());	
		System.out.println(testing.getState().get(0).get(4).moves());
		
		//should be B
		System.out.println(testing.getState().get(0).get(5).toString());	
		System.out.println(testing.getState().get(0).get(5).moves());

		//should be K
		System.out.println(testing.getState().get(0).get(6).toString());	
		System.out.println(testing.getState().get(0).get(6).moves());
		
		//should be R
		System.out.println(testing.getState().get(0).get(7).toString());	
		System.out.println(testing.getState().get(0).get(7).moves());
		
		//should be R
		System.out.println(testing.getState().get(7).get(0).toString());	
		System.out.println(testing.getState().get(7).get(0).moves());
		
		//should be K
		System.out.println(testing.getState().get(7).get(1).toString());	
		System.out.println(testing.getState().get(7).get(1).moves());
		
		//should be B
		System.out.println(testing.getState().get(7).get(2).toString());	
		System.out.println(testing.getState().get(7).get(2).moves());
		
		//should be S
		System.out.println(testing.getState().get(7).get(3).toString());	
		System.out.println(testing.getState().get(7).get(3).moves());
		
		//should be Q
		System.out.println(testing.getState().get(7).get(4).toString());	
		System.out.println(testing.getState().get(7).get(4).moves());
		
		//should be B
		System.out.println(testing.getState().get(7).get(5).toString());	
		System.out.println(testing.getState().get(7).get(5).moves());

		//should be K
		System.out.println(testing.getState().get(7).get(6).toString());	
		System.out.println(testing.getState().get(7).get(6).moves());
		
		//should be R
		System.out.println(testing.getState().get(7).get(7).toString());	
		
		launch(args);
	}
}
