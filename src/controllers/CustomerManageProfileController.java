package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.main;
//import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import models.CustomerModel;
import models.DialogModel;
import models.ViewsRouting;

public class CustomerManageProfileController implements Initializable {
	@FXML
	private AnchorPane customermanageprofile;
	@FXML
	private TextField txtCustomerRegisterName;
	@FXML
	private TextField txtCustomerRegisterUsername;
	@FXML
	private TextField txtCustomerRegisterEmail;
	@FXML
	private PasswordField txtCustomerRegisterPassword;
	@FXML
	private TextField txtCustomerRegisterAge;
	@FXML
	private TextField txtCustomerRegisterCity;
	@FXML
	private TextField txtCustomerRegisterState;
	@FXML
	private TextField txtCustomerRegisterPincode;
	@FXML
	private StackPane customerstackepanemanageprofile;
	@FXML
	private Label customermanagelblError;

	// Declare DB objects
	static Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	String loginUserName;
	String loginUserPass;
	CustomerModel model = null;
	DialogModel dialog = null;

	public CustomerManageProfileController() {
		OracleConnection = Main.OracleConnection;
		viewr = new ViewsRouting();
		dialog = new DialogModel();
		model = new CustomerModel();
	}

	public void initData(String username, String password) {
		this.loginUserName = username;
		this.loginUserPass = password;
		model.getCustomerDetails(username, password);
		this.txtCustomerRegisterUsername.setText(username);
		this.txtCustomerRegisterPassword.setText(password);
		this.txtCustomerRegisterAge.setText(model.getCustomerAge() + "");
		this.txtCustomerRegisterCity.setText(model.getCustomerCity());
		this.txtCustomerRegisterState.setText(model.getCustomerState());
		this.txtCustomerRegisterName.setText(model.getCustomerName());
		this.txtCustomerRegisterEmail.setText(model.getCustomerEmail());
		this.txtCustomerRegisterPincode.setText(model.getCustomerPincode() + "");
	}

	@FXML
	private void onCustomerProfileUpdate() {
		try {
			// Execute a query

			customermanagelblError.setText("");
			String username = this.txtCustomerRegisterUsername.getText();
			String password = this.txtCustomerRegisterPassword.getText();
			String name = this.txtCustomerRegisterName.getText();

			// Validations
			if (name == null || name.trim().equals("")) {
				customermanagelblError.setText("Name Cannot be empty or spaces");
				return;
			}
			if (username == null || username.trim().equals("")) {
				customermanagelblError.setText("Username Cannot be empty or spaces");
				return;
			}
			if (password == null || password.trim().equals("")) {
				customermanagelblError.setText("Password Cannot be empty or spaces");
				return;
			}

			if (username == null || username.trim().equals("") && name == null
					|| name.trim().equals("") && (password == null || password.trim().equals(""))) {
				customermanagelblError.setText("Name / Username / Password Cannot be empty or spaces");
				return;
			}

				stmt = OracleConnection.createStatement();
			String sql = null;

			int pinCode = txtCustomerRegisterPincode.getText().equals("") ? 0
					: Integer.parseInt(txtCustomerRegisterPincode.getText());
			int age = txtCustomerRegisterAge.getText().equals("") ? 0
					: Integer.parseInt(txtCustomerRegisterAge.getText());

			sql = "update customers_2711 set password='" + txtCustomerRegisterPassword.getText()
					+ "', username='" + txtCustomerRegisterUsername.getText() + "',name='"
					+ txtCustomerRegisterName.getText() + "',pincode=" + pinCode + ",state='"
					+ txtCustomerRegisterState.getText() + "',city='" + txtCustomerRegisterCity.getText()
					+ "',email='" + txtCustomerRegisterEmail.getText() + "',age=" + age
					+ " where username='" + this.loginUserName + "' ;";

			int count = stmt.executeUpdate(sql);
			if (count > 0) {

				dialog.handleDialogCustomerReservation("Success", "Customer Updated Successfully!",
						customerstackepanemanageprofile, "/views/CustomerPageView.fxml", loginUserName, loginUserPass);

			}
			OracleConnection.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@FXML
	private void onCustomerProfileReset() {
		this.txtCustomerRegisterName.clear();
		this.txtCustomerRegisterUsername.clear();
		this.txtCustomerRegisterEmail.clear();
		this.txtCustomerRegisterPassword.clear();
		this.txtCustomerRegisterAge.clear();
		this.txtCustomerRegisterCity.clear();
		this.txtCustomerRegisterState.clear();
		this.txtCustomerRegisterPincode.clear();
	}

	@FXML
	private void onCustomerProfileBack() {
		viewr.handleRoutingCustomerPage("/views/CustomerPageView.fxml", customerstackepanemanageprofile, loginUserName,
				loginUserPass,"Customer Home");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
