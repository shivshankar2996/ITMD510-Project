package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class AdminManagerModel extends DBConnect {
	private int aid;
	private String aname;
	private String aemail;
	private String astatus;
	private Button actionButton;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminManagerModel() {
		conn = new DBConnect();
	}

	/**
	 * @return the aid
	 */
	public int getAid() {
		return aid;
	}

	/**
	 * @param aid the aid to set
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * @return the aname
	 */
	public String getAname() {
		return aname;
	}

	/**
	 * @param aname the aname to set
	 */
	public void setAname(String aname) {
		this.aname = aname;
	}

	/**
	 * @return the aemail
	 */
	public String getAemail() {
		return aemail;
	}

	/**
	 * @param aemail the aemail to set
	 */
	public void setAemail(String aemail) {
		this.aemail = aemail;
	}

	/**
	 * @return the astatus
	 */
	public String getAstatus() {
		return astatus;
	}

	/**
	 * @param astatus the astatus to set
	 */
	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

	/**
	 * @return the actionButton
	 */
	public Button getActionButton() {
		return actionButton;
	}

	/**
	 * @param actionButton the actionButton to set
	 */
	public void setActionButton(Button actionButton) {
		this.actionButton = actionButton;
	}

	public ObservableList<AdminManagerModel> getAdmins() {
		ObservableList<AdminManagerModel> admins = FXCollections.observableArrayList();
		String query = "SELECT adminid, adminname, adminemail,adminstatus FROM hotel_reserv_admin";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AdminManagerModel admin = new AdminManagerModel();
				// grab record data by table field name into ClientModel account object
				admin.setAid(resultSet.getInt("adminid"));
				admin.setAname(resultSet.getString("adminname"));
				admin.setAemail(resultSet.getString("adminemail"));
				admin.setAstatus(resultSet.getString("adminstatus"));
//				admin.setActionButton(new Button("Make Admin"));
				admins.add(admin); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Admin Info: " + e);
		}
		return admins; // return arraylist
	}

	public void updateTable(int id) {
		String query = "update hotel_reserv_admin set adminstatus = true where adminid=" + id + ";";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Updated successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
