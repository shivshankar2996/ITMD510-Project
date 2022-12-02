package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import app.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import models.CRUDModel;
import models.ViewsRouting;

public class AdminLoginController {

	@FXML
	private StackPane admin_view_stackpane;

	@FXML
	private AnchorPane admin_view_pane;

	@FXML
	private TextField txtAdminUsername;
	@FXML
	private PasswordField txtAdminPassword;
	@FXML
	private Label lblError;
   
	// Declare DB objects
	static Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	CRUDModel model = null;
	public AdminLoginController() throws SQLException {
		OracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QRVS9B0:1521:xe","system","SHankar$1996");
		viewr = new ViewsRouting();
		model = new CRUDModel();
	}

	
	


	public void onAdminLogin() throws SQLException {
		String username = this.txtAdminUsername.getText();
		String password = this.txtAdminPassword.getText();
System.out.println(password);
System.out.println(username);
		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}
     	// authentication check
		checkCredentials(username, password);
		
		
	}

	@FXML
	public void onAdminRegister() {
		viewr.handleRouting("/views/AdminRegisterView.fxml", admin_view_pane,"Admin Registration");

	}

	@FXML
	public void onAdminBackButton() {
		viewr.handleRouting("/views/HomescreenView.fxml", admin_view_pane,"Home");
        
	}

	private void checkCredentials(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		Boolean isValid = model.getCredentials(username, password);
		System.out.println(username);
		System.out.println(password);
		System.out.println(model.isAdmin());
		System.out.println(isValid);
		if (!isValid) {
			lblError.setText("User does not exist!");
			return;
		}
		try {
			//if (model.isAdmin() && isValid) {
			if (isValid) {
				// If user is admin, inflate admin view
				viewr.handleRoutingAdminPage("/views/AdminPageView.fxml", admin_view_pane, username,  password, "Admin Home");
			}

		} 
		catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		

	}

}
