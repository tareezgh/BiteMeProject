package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Delivery;
import logic.Order;
import logic.OrderAndDelivery;
import logic.Restaurant;

public class CustomerViewOrderFormController implements Initializable {
	String selectedOrder;
	String selectedStatus;
	String selectedRestaurant;

	ArrayList<String> addRefundItems = new ArrayList<String>(); // Id ,Restaurant Name , Amount
	ArrayList<String> getOrderDetailsItems = new ArrayList<String>(); // customerID, Order Number,DiffTime , status to
	ArrayList<String> getRefundDetailsItems = new ArrayList<String>(); // UserId, Restaurant Name // be change
	ArrayList<String> changeOrderStatusItems = new ArrayList<String>(); // customerID, status to be change

	ArrayList<String> getItems = new ArrayList<String>(); // UserID,OrderNum , MealNum

	ArrayList<OrderAndDelivery> OrderAndDeliveryList = new ArrayList<OrderAndDelivery>();

	ArrayList<Order> ordersListByID;
	
	HashMap<String, String> OrderNumHashMap = new HashMap<>(); // key= user ID , value = order number
	String UserID;
	String OrderNumber;
	
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblMessage;
	@FXML
	private Button btnPickUp;
	@FXML
	private Button btnBack;
	@FXML
	private TableView<OrderAndDelivery> OrderTbl = new TableView<OrderAndDelivery>();

	@FXML
	private TableColumn<OrderAndDelivery, String> orderNumColumn;
	@FXML
	private TableColumn<OrderAndDelivery, String> statusColumn;
	@FXML
	private TableColumn<OrderAndDelivery, String> restaurantColumn;
	@FXML
	private TableColumn<OrderAndDelivery, String> dateColumn;
	@FXML
	private TableColumn<OrderAndDelivery, String> timeColumn;
	@FXML
	private TableColumn<OrderAndDelivery, String> deliveryTypeColumn;


	/**
	 * Method to set the title and to show the order in a table
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText("   " + UserController.userName + " Orders");
		UserID = UserController.userID;
		getItems.add(0, UserController.userID);
		displayTable();

	}

	

	@FXML
	public void getSelectedOrderDetails(MouseEvent event) {
		selectedOrder = OrderTbl.getSelectionModel().getSelectedItem().getOrderNumber();
		selectedStatus = OrderTbl.getSelectionModel().getSelectedItem().getStatus();
	}

	/**
	 * Method that changes the status of the order after picking it up
	 * Calculated the approve time and the time that the customer picks up the order and 
	 * Checks if there's a refund
	 * @param event - On Action
	 * @throws Exception
	 */
	@FXML
	public void getPickUpBtn(ActionEvent event) throws Exception {
		changeOrderStatusItems.add(0, UserController.userID);
		changeOrderStatusItems.add(1, selectedOrder);

		if (selectedStatus.equals("Ready")) {
			getOrderDetailsItems.add(0, UserController.userID);
			getOrderDetailsItems.add(1, selectedOrder);
			OrderController.pickTime = String.valueOf(LocalTime.now());

			for (int i = 0; i < ordersListByID.size(); i++) {
				if (ordersListByID.get(i).getOrderNumber().equals(selectedOrder)) {
					OrderController.approveTime = ordersListByID.get(i).getApproveTimeByRestaurant();
					OrderController.totalPriceForRefund += Integer.valueOf(ordersListByID.get(i).getMealPrice());
				}
			}

			String[] ApproveTime = OrderController.approveTime.split(":");
			int ApproveHours = Integer.valueOf(ApproveTime[0]);
			int ApproveMinute = Integer.valueOf(ApproveTime[1]);

			String[] PickTime = OrderController.pickTime.split(":");
			int PickHours = Integer.valueOf(PickTime[0]);
			int PickMinute = Integer.valueOf(PickTime[1]);

			int DiffTime = (PickHours * 60 + PickMinute) - (ApproveHours * 60 + ApproveMinute);
			System.out.println("DiffTime " + DiffTime);
			getOrderDetailsItems.add(2, String.valueOf(DiffTime));
			OrderController.UpdateAveragePrepareTime(getOrderDetailsItems);

			// ******

			selectedRestaurant = OrderTbl.getSelectionModel().getSelectedItem().getRestaurantName();
			String[] resName = selectedRestaurant.split("_");
			addRefundItems.add(0, UserController.userID);
			addRefundItems.add(1, resName[0]);

			changeOrderStatusItems.add(2, "Done");
			if (OrderController.discountFlag == true) {
				if (DiffTime >= 20) { // 50% 

					addRefund();
					changeOrderStatusItems.add(2, "Late"); 
				}

			} else {
				if (DiffTime >= 60) { // 50% 
					addRefund();
					changeOrderStatusItems.add(2, "Late"); 
				}
			}

			if (UserController.changeOrderStatus(changeOrderStatusItems)) {
				displayTable();
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status has changed");
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("You can't pickup the order");
			}
		} else {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("The order isn't ready yet!");
		}

	}

	/**
	 * Method take us back to the previous page
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Customer page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Method to set a table and loading the data in the table
	 */
	private void displayTable() {
		ordersListByID = OrderController.getOrderDetailsByID(UserController.userID);
		ArrayList<Order> ordersListByIDWithoutDuplicates = new ArrayList<Order>();
		ArrayList<Delivery> delievryList = OrderController.getDeliveryDetails(UserController.userID);

		for (int i = 0; i < ordersListByID.size(); i++) {

			OrderNumber = ordersListByID.get(i).getOrderNumber();

			if (!OrderNumHashMap.containsValue(OrderNumber)) {
				OrderNumHashMap.put(UserID, OrderNumber);
				if (ordersListByID.get(i).getStatus().equals("Hold")
						|| ordersListByID.get(i).getStatus().equals("Approve")
						|| ordersListByID.get(i).getStatus().equals("Ready"))
					ordersListByIDWithoutDuplicates.add(ordersListByID.get(i));

			}
		}

		for (int i = 0; i < ordersListByIDWithoutDuplicates.size(); i++) {
			String orderNumber = ordersListByIDWithoutDuplicates.get(i).getOrderNumber();
			String status = ordersListByIDWithoutDuplicates.get(i).getStatus();
			String restaurantName = ordersListByIDWithoutDuplicates.get(i).getRestaurantName();
			String dateOfOrder = ordersListByIDWithoutDuplicates.get(i).getDateOfOrder();
			String typeOfDelivery = delievryList.get(i).getTypeOfDelivery();
			String time = delievryList.get(i).getTime();

			OrderAndDelivery Details = new OrderAndDelivery(orderNumber, status, restaurantName, dateOfOrder,
					typeOfDelivery, time);
			OrderAndDeliveryList.add(Details);

		}
		
		

		// Set Table
		ObservableList<OrderAndDelivery> data = FXCollections.observableArrayList(OrderAndDeliveryList);

		orderNumColumn.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("RestaurantName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("DateOfOrder"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
		deliveryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfDelivery"));

		OrderTbl.setItems(data);
	}
	
	private void addRefund() {
		OrderController.totalPriceForRefund *= 0.5;
		System.out.println("totalPriceForRefund " + OrderController.totalPriceForRefund);

		String parts[] = OrderTbl.getSelectionModel().getSelectedItem().getRestaurantName().split("_");
		OrderController.selectedRestaurantName = parts[0];
		getRefundDetailsItems.add(0, UserController.userID);
		getRefundDetailsItems.add(1, OrderController.selectedRestaurantName);
		ArrayList<String> getRefundDetailsList = OrderController.getRefundDetails(getRefundDetailsItems);

		System.out.println("getRefundDetailsList " + getRefundDetailsList);

		for (int i = 0; i < getRefundDetailsList.size(); i++) {
			String[] refund = getRefundDetailsList.get(i).split("_");
			String Id = refund[0];
			String RestaurantName = refund[1];
			String RefundAmount = refund[2];
			String Key = Id + "_" + RestaurantName;
			String CurrentKey = UserController.userID + "_" + OrderController.selectedRestaurantName;

			if (Key.equals(CurrentKey)) {
				getRefundDetailsItems.add(2, String.valueOf(OrderController.totalPriceForRefund));
				boolean UpdateRefundDetailsFlag = OrderController.UpdateRefundDetails(getRefundDetailsItems);
				if (UpdateRefundDetailsFlag) {
					System.out.println("UpdateRefundDetailsFlag: " + UpdateRefundDetailsFlag);
				}
			} else {
				addRefundItems.add(2, String.valueOf(OrderController.totalPriceForRefund));
				boolean refundFlag = OrderController.addRefund(addRefundItems);
				if (refundFlag) {
					System.out.println("add refund Flag " + refundFlag);
				}
			}

		}

	}

	

}
