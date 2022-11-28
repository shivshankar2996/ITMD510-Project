package controllers;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import dao.DBConnect;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.RoomModel;
import models.ViewsRouting;

public class CustomerAvailableRoomController implements Initializable {

	@FXML
	private StackPane stackpane_custavailablerooms;

	@FXML
	private TableView<RoomModel> tblRooms;
	@FXML
	private TableColumn<RoomModel, String> roomId;
	@FXML
	private TableColumn<RoomModel, String> roomType;
	@FXML
	private TableColumn<RoomModel, String> roomNumber;
	@FXML
	private TableColumn<RoomModel, String> numberOfPeople;
	@FXML
	private TableColumn<RoomModel, String> roomPrice;
	@FXML
	private TableColumn<RoomModel, String> roomStatus;

	@FXML
	private TextField txtSearch;

	@FXML
	private Button btnSearchRoomNo;

	@FXML
	private Button btnMakeAvail;

	@FXML
	private Label lblSearch;

	private ObservableList<RoomModel> roomList;

	DBConnect conn = null;
	Statement stmt = null;
	ViewsRouting viewr = null;
	RoomModel roomModel = null;
	String loginUserName = null;
	String loginUserPass = null;

	public CustomerAvailableRoomController() {
		conn = new DBConnect();
		viewr = new ViewsRouting();
		roomModel = new RoomModel();
	}
	
	public void initData(String username, String password) {
		this.loginUserName = username;
		this.loginUserPass = password;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM  rooms_2711";
		roomList = roomModel.getRooms(query);
		loadData(query);
	}

	public void loadData(String query) {

		roomId.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomId"));
		roomType.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomType"));
		roomNumber.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomNumber"));
		numberOfPeople.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("numberOfPeople"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomPrice"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomStatus"));

		// auto adjust width of columns depending on their content
		tblRooms.setItems(roomList);
		tblRooms.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblRooms));
	}

	public void customResize(TableView<?> view) {
		AtomicLong width = new AtomicLong();
		view.getColumns().forEach(col -> {
			width.addAndGet((long) col.getWidth());
		});
		double tableWidth = view.getWidth();

		if (tableWidth > width.get()) {
			view.getColumns().forEach(col -> {
				col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
			});
		}
	}

	
	@FXML
	public void onCustomerAvaibleRoomBack() {
		viewr.handleRoutingCustomerPage("/views/CustomerPageView.fxml", stackpane_custavailablerooms, loginUserName, loginUserPass,"Customer Home");

	}

	@FXML
	public void onCustomerAvaibleRoomExit() {
		System.exit(0);
	}

}
