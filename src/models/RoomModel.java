package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomModel {
	String roomId;
	String roomType;
	String roomNumber;
	String numberOfPeople;
	String roomPrice;
	String roomStatus;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public RoomModel() {
		conn = new DBConnect();

	}

	public RoomModel(String id, String roomType, String roomNumber, String numberOfPeople, String roomPrice,
			String roomStatus) {
		super();
		this.roomId = id;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.roomPrice = roomPrice;
		this.roomStatus = roomStatus;
	}

	/**
	 * @return the id
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param id the id to set
	 */
	public void setRoomId(String id) {
		this.roomId = id;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the numberOfPeople
	 */
	public String getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * @return the roomPrice
	 */
	public String getRoomPrice() {
		return roomPrice;
	}

	/**
	 * @param roomPrice the roomPrice to set
	 */
	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * @return the roomStatus
	 */
	public String getRoomStatus() {
		return roomStatus;
	}

	/**
	 * @param roomStatus the roomStatus to set
	 */
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public ObservableList<RoomModel> getRooms(String query) {
		ObservableList<RoomModel> rooms = FXCollections.observableArrayList();
		try (PreparedStatement statement = conn.getConnection().prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				RoomModel room = new RoomModel();
				// grab record data by table field name into ClientModel account object
				room.setRoomId(resultSet.getInt("roomid") + "");
				room.setRoomType(resultSet.getString("roomtype"));
				room.setNumberOfPeople(resultSet.getInt("numberofpeople") + "");
				room.setRoomNumber(resultSet.getInt("roomnumber") + "");
				room.setRoomPrice(resultSet.getInt("roomprice") + "");
				room.setRoomStatus(resultSet.getString("roomstatus"));
				rooms.add(room);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Room Info: " + e);
		}
		return rooms;
	}

	public int updateRoomByRoomNumber(int roomNumber) {
		int result = 0;
		String query = "UPDATE  rooms_2711 SET roomstatus=? WHERE roomnumber=?";
		try (PreparedStatement statement = conn.getConnection().prepareStatement(query)) {
			statement.setString(1, "available");
			statement.setInt(2, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Updating Room Info: " + e);
		}
		return result;
	}
	
	public int DeletedRoomByRoomNumber(int roomNumber) {
		int result = 0;
		String query = "Delete from rooms_2711 WHERE roomnumber=?";
		try (PreparedStatement statement = conn.getConnection().prepareStatement(query)) {
			statement.setInt(1, roomNumber);
			//statement.setInt(2, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Deleting Room Info: " + e);
		}
		return result;
	}
	
	public void findRoomByRoomNumber(int roomNumber) {
		String query = "select * from  rooms_2711 WHERE roomstatus='available' and roomnumber=?;";
		try (PreparedStatement statement = conn.getConnection().prepareStatement(query)) {
			statement.setInt(1, roomNumber);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				setRoomId(resultSet.getInt("roomid") + "");
				setRoomType(resultSet.getString("roomtype"));
				setNumberOfPeople(resultSet.getInt("numberofpeople") + "");
				setRoomNumber(resultSet.getInt("roomnumber") + "");
				setRoomPrice(resultSet.getInt("roomprice") + "");
				setRoomStatus(resultSet.getString("roomstatus"));
			}

		} catch (SQLException e) {
			System.out.println("Error fetching Room Info: " + e);
		}
	
	}

}
