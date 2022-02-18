package gui;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Customer;
import logic.Delivery;
import logic.Order;

public class RestaurantApproveOrderFormController implements Initializable {
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblCurrentStatus;

	@FXML
	private ComboBox<String> cmpIdOrderNumHold;
	private ObservableList<String> IdOrderNumHoldList;
	ArrayList<String> IdOrderNumHold = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpIdOrderNumApproved;
	private ObservableList<String> IdOrderNumApprovedList;
	ArrayList<String> IdOrderNumApprove = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> StatusList;
	ArrayList<String> Status = new ArrayList<String>();

	@FXML
	private TableView<Order> OrderToApprove = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> mealsColumn;
	@FXML
	private TableColumn<Order, String> levelOfCookColumn;
	@FXML
	private TableColumn<Order, String> retictionsColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;
	@FXML
	private TableColumn<Order, String> statusColumn;

	ArrayList<String> getOrderDetailsItems = new ArrayList<String>(); // Order Number, status to be change

	private String selectedStatus;
	private String selectedNewStatus;
	String iD;
	String OrderNum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();

	}

	/**
	 * Method to get the selected hold order
	 */
	@FXML
	public void getSelectedHoldOrder() {
		String ID_OrderNum = cmpIdOrderNumHold.getValue();
		getOrder(ID_OrderNum);
		displayTable();
		Status.add("Approve");
		Status.add("Decline");
		StatusList = FXCollections.observableArrayList(Status);
		cmpStatus.setItems(StatusList);
		cmpStatus.setDisable(false);
	}

	/**
	 * Method to get the approve hold order
	 */
	@FXML
	public void getSelectedApproveOrder() {
		String ID_OrderNum = cmpIdOrderNumApproved.getValue();
		System.out.println("ID_OrderNum " + ID_OrderNum);
		getOrder(ID_OrderNum);

		displayTable();

		Status.add("Ready");
		StatusList = FXCollections.observableArrayList(Status);
		cmpStatus.setItems(StatusList);
		cmpStatus.setDisable(false);
	}

	/**
	 * Method to set the data in the combo box and display by IdNumber_OrderNumber and status
	 */
	private void setComboBox() {
		String Key = UserController.firstName + "_" + UserController.address;
		ArrayList<Customer> usersList = UserController.getAllCustomerDetails("Customer");
		System.out.println("usersList " + usersList);
		HashMap<String, String> OrderNumHashMap = new HashMap<>();
		String UserID;
		String OrderNumber;

		// Display by IdNumber_OrderNumber and status
		for (int i = 0; i < usersList.size(); i++) {
			UserID = usersList.get(i).getCustomerID();
			ArrayList<Order> ordersListByID = OrderController.getOrderDetailsByID(UserID);
			for (int j = 0; j < ordersListByID.size(); j++) {
				OrderNumber = ordersListByID.get(j).getOrderNumber();
				if (ordersListByID.get(j).getStatus().equals("Hold")
						&& ordersListByID.get(i).getRestaurantName().equals(Key)) {
					if (!OrderNumHashMap.containsValue(OrderNumber)) {
						OrderNumHashMap.put(UserID, OrderNumber);
						IdOrderNumHold.add(UserID + "_" + OrderNumber);
					}
				} else if (ordersListByID.get(j).getStatus().equals("Approve")
						&& ordersListByID.get(i).getRestaurantName().equals(Key)) {
					if (!OrderNumHashMap.containsValue(OrderNumber)) {
						OrderNumHashMap.put(UserID, OrderNumber);
						IdOrderNumApprove.add(UserID + "_" + OrderNumber);
					}
				}
			}
		}

		IdOrderNumApprovedList = FXCollections.observableArrayList(IdOrderNumApprove);
		cmpIdOrderNumApproved.setItems(IdOrderNumApprovedList);

		IdOrderNumHoldList = FXCollections.observableArrayList(IdOrderNumHold);
		cmpIdOrderNumHold.setItems(IdOrderNumHoldList);

	}

	/** Method to get the order and splits the ID and orderNum
	 * @param ID_OrderNum
	 */
	public void getOrder(String ID_OrderNum) {
		System.out.println("getOrder ID_OrderNum " + ID_OrderNum);
		if (!(ID_OrderNum == null)) {
			String[] parts = ID_OrderNum.split("_");
			iD = parts[0];
			OrderNum = parts[1];
			getOrderDetailsItems.add(0, iD);
			getOrderDetailsItems.add(1, OrderNum);
		}
	}

	/**
	 * Method that displays the data in a table 
	 */
	public void displayTable() {

		ArrayList<Order> ordersList = OrderController.getOrderDetailsByIdAndOrderNum(getOrderDetailsItems);
		ObservableList<Order> data = FXCollections.observableArrayList(ordersList);

		mealsColumn.setCellValueFactory(new PropertyValueFactory<>("meal"));
		levelOfCookColumn.setCellValueFactory(new PropertyValueFactory<>("lvlOfCook"));
		retictionsColumn.setCellValueFactory(new PropertyValueFactory<>("restrictions"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));

		selectedStatus = ordersList.get(0).getStatus();
		this.lblCurrentStatus.setText(selectedStatus);

		OrderToApprove.setItems(data);

	}

	/**
	 * Method to get selected satatus and saves it
	 */
	@FXML
	public void getSelectedStatus() {
		cmpStatus.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		selectedNewStatus = cmpStatus.getValue();
		getOrderDetailsItems.add(2, selectedNewStatus); // save new status

	}

	/**
	 * Method that updates the status of an order
	 * Checks if the user selected an order first
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getChangeBtn(MouseEvent event) throws Exception {
		if (selectedNewStatus == null) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must select order status");
			cmpStatus.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		} else if (selectedStatus.equals(selectedNewStatus)) {
			lblMessage.setTextFill(Color.BLACK);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("The current status is " + selectedNewStatus);

		} else {

			boolean changed = UserController.changeOrderStatus(getOrderDetailsItems);
			if (selectedNewStatus.equals("Approve")) {
				OrderController.approveTime = String.valueOf(LocalTime.now());
				getOrderDetailsItems.add(2, OrderController.approveTime); // save new status
				OrderController.changeOrderApproveTime(getOrderDetailsItems);
				System.out.println("OrderController.ApproveTime " + OrderController.approveTime);
			}

			cmpStatus.setDisable(true);
			if (changed) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status has changed to " + selectedNewStatus);

				if (selectedNewStatus.equals("Ready")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("New status");
					alert.setHeaderText("The order is ready");
					alert.setContentText("Now " + iD + " customer can pick up the order " + OrderNum);
					alert.showAndWait();
				}

			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status not changed");
			}
			cmpStatus.setValue(" ");
			IdOrderNumApprove.clear();
			IdOrderNumHold.clear();
			Status.clear();
			setComboBox();
			displayTable();

		}

	}

	
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Restaurant page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
