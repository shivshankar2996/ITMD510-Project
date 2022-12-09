package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
		    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/HomescreenView.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Home");
			stage.setScene(scene);
			stage.getIcons().add(new Image("/images/009-hotel.png"));

			stage.show();
			
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
