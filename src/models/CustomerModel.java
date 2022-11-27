package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

public class CustomerModel extends DBConnect {

	private Boolean customerStatus;
	private int id;

	private String customerName;

	private String customerUsername;

	private String customerEmail;

	private String customerPassword;

	private int customerAge;

	private String customerCity;

	private String customerState;

	private int customerPincode;
	
	
	



	/**
	 * @return the customer
	 */
	public Boolean getCustomerStatus() {
		return customerStatus;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomerStatus(Boolean customerStatus) {
		this.customerStatus = customerStatus;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerUsername
	 */
	public String getCustomerUsername() {
		return customerUsername;
	}

	/**
	 * @param customerUsername the customerUsername to set
	 */
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the customerPassword
	 */
	public String getCustomerPassword() {
		return customerPassword;
	}

	/**
	 * @param customerPassword the customerPassword to set
	 */
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	/**
	 * @return the customerAge
	 */
	public int getCustomerAge() {
		return customerAge;
	}

	/**
	 * @param customerAge the customerAge to set
	 */
	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

	/**
	 * @return the customerCity
	 */
	public String getCustomerCity() {
		return customerCity;
	}

	/**
	 * @param customerCity the customerCity to set
	 */
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	/**
	 * @return the customerState
	 */
	public String getCustomerState() {
		return customerState;
	}

	/**
	 * @param customerState the customerState to set
	 */
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	/**
	 * @return the customerPincode
	 */
	public int getCustomerPincode() {
		return customerPincode;
	}

	/**
	 * @param customerPincode the customerPincode to set
	 */
	public void setCustomerPincode(int customerPincode) {
		this.customerPincode = customerPincode;
	}

	public Boolean getCredentials(String username, String password) {

		String query = "SELECT * FROM customers WHERE username = ? and password= ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				setCustomerStatus(rs.getBoolean("status"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean getCustomerDetails(String username, String password) {

		String query = "SELECT * FROM customers WHERE username = ? and password= ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				setId(rs.getInt("id"));
				setCustomerStatus(rs.getBoolean("status"));
				setCustomerAge(rs.getInt("age"));
				setCustomerName(rs.getString("name"));
				setCustomerEmail(rs.getString("email"));
				setCustomerCity(rs.getString("city"));
				setCustomerState(rs.getString("state"));
				setCustomerPassword(rs.getString("password"));
				setCustomerUsername(rs.getString("username"));
				setCustomerPincode(rs.getInt("pincode"));
				
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void getCustomerDetailsUsingName(String custname) {
		
//		CustomerModel custModel = null;
		String query = "SELECT * FROM customers WHERE name = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, custname);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("id"));
				setCustomerStatus(rs.getBoolean("status"));
				setCustomerAge(rs.getInt("age"));
				setCustomerName(rs.getString("name"));
				setCustomerEmail(rs.getString("email"));
				setCustomerCity(rs.getString("city"));
				setCustomerState(rs.getString("state"));
				setCustomerPassword(rs.getString("password"));
				setCustomerUsername(rs.getString("username"));
				setCustomerPincode(rs.getInt("pincode"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
