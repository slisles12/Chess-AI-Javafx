package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
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
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
