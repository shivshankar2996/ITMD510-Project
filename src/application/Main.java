package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage; // set global stage object!!!
	public static Connection OracleConnection;

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

	public static void main(String[] args)throws Exception {
		launch(args);
		Class.forName("oracle.jdbd.driver.OracleDriver");
		OracleConnection =  DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-R63JOEHG:1522:xe","system","Oracle123");
		Statement st = OracleConnection.createStatement();
		String query = "select * from admins_2711";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+""+rs.getString(2));
		}
		rs.close();
		st.close();
		OracleConnection.close();
	}
}
