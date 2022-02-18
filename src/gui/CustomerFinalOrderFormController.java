package gui;

import java.io.IOException;

import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import logic.Order;

public class CustomerFinalOrderFormController implements Initializable {
	ArrayList<Order> finalOrdersList = new ArrayList<Order>();
	ArrayList<String> getOrderDetailsItems = new ArrayList<String>(); // UserID,OrderNum , MealNum
	ArrayList<String> getRefundDetailsItems = new ArrayList<String>(); // UserId, Restaurant Name

	private boolean refundFlag = false;
	private String refundAmount;

	@FXML
	private Label lblSubtotal;
	@FXML
	private Label lblShipping;
	@FXML
	private Label lblDiscount;
	@FXML
	private Label lblTotalPrice;
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblRefundAmount;
	@FXML
	private Button BtnUse;

	@FXML
	private TableView<Order> SummaryTable = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> mealColumn;
	@FXML
	private TableColumn<Order, String> quantityColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;

	/**
	 * Method that shows the final calculates the billing amount
	 * Calculates the total price if the customer got discounts
	 * Split the date into years, months and days
	 * Calculating the shipping price according the the type of the delivery
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.lblSubtotal.setFont(new Font("Arial", 13));
		this.lblSubtotal.setText(String.valueOf(OrderController.totalPrice));
		lblRefundAmount.setText("0");
		BtnUse.setDisable(true);

		// *** CALCULATE BUDGET

		System.out.println("UserController.accountType " + UserController.accountType);
		if (UserController.accountType.equals("Business")) {
			int subtract = OrderController.totalPrice - Integer.valueOf(UserController.billing);
			if ((Integer.valueOf(UserController.billing) < OrderController.totalPrice)
					&& (Integer.valueOf(UserController.billing) != 0)) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error paying");
				alert.setHeaderText("You don't have a budget to pay for this order");
				alert.setContentText("Remain " + subtract + " to pay from your credit card");
				alert.showAndWait();
			}
		}

		// *** CALCULATE TOTAL PRICE BY DISCOUNTS

		OrderController.currentTime = String.valueOf(LocalTime.now());

		OrderController.currentDate = String.valueOf(LocalDate.now()); // time of order // LocalDate.now();

		String[] SystemcurrentTime = OrderController.currentTime.split(":");
		int SystemHours = Integer.valueOf(SystemcurrentTime[0]);
		int SystemMinute = Integer.valueOf(SystemcurrentTime[1]);

		String[] currentTime = OrderController.selectedTime.split(":");
		int Hours = Integer.valueOf(currentTime[0]);
		int Minute = Integer.valueOf(currentTime[1]);

		int DiffTime = (Hours * 60 + Minute) - (SystemHours * 60 + SystemMinute);

		// *** CALCULATE YEAR
		String[] SystemcurrentDate = OrderController.currentDate.split("-");
		int SystemYear = Integer.valueOf(SystemcurrentDate[0]);
		int SystemMonth = Integer.valueOf(SystemcurrentDate[1]);
		int SystemDay = Integer.valueOf(SystemcurrentDate[2]);

		String[] currentDate = OrderController.selectedDate.split("-");
		int Year = Integer.valueOf(currentDate[0]);
		int Month = Integer.valueOf(currentDate[1]);
		int Day = Integer.valueOf(currentDate[2]);

		if (Month > SystemMonth) {
			getDiscount();
		} else if (SystemMonth == 12 && Month == 01) {
			getDiscount();
		} else if (Day > SystemDay) {
			getDiscount();
		} else if (DiffTime >= 120) {
			getDiscount();
		} else {
			OrderController.totalPriceForRefund = OrderController.totalPrice;
			this.lblDiscount.setText("No discount");
		}

		/// *** CALCULATE SHIPPING 
		if (OrderController.selectedType == "Take-Away") {
			this.lblShipping.setFont(new Font("Arial", 13));
			this.lblShipping.setText("0");

		} else {
			switch (OrderController.selecteDeliveryType) {

			case "Regular":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("25");
				OrderController.totalPrice += 25;
				break;

			case "Shared":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("25");
				OrderController.totalPrice += 25;
				break;

			case "Robot":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("0");
				OrderController.totalPrice += 0;
				break;

			}

		}

		// *** CHECK IF REFUNF EXISTS

		getRefundDetailsItems.add(0, UserController.userID);
		getRefundDetailsItems.add(1, OrderController.selectedRestaurantName);

		ArrayList<String> getRefundDetailsList = OrderController.getRefundDetails(getRefundDetailsItems);

		System.out.println("RefundFlag " + getRefundDetailsList);

		for (int i = 0; i < getRefundDetailsList.size(); i++) {
			String[] refund = getRefundDetailsList.get(i).split("_");
			String Id = refund[0];
			String RestaurantName = refund[1];
			refundAmount = refund[2];
			String Key = Id + "_" + RestaurantName;
			String CurrentKey = UserController.userID + "_" + OrderController.selectedRestaurantName;

			System.out.println("RefundAmount: " + refundAmount);

			if (Key.equals(CurrentKey)) {
				lblRefundAmount.setText(refundAmount);
				if (Integer.valueOf(refundAmount) == 0) { // || OrderController.totalPrice == 0
					BtnUse.setDisable(true);
				} else {
					BtnUse.setDisable(false);
				}
			}
		}

		
		this.lblTotalPrice.setFont(new Font("Arial", 20));
		this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));
		getOrderDetailsItems.add(0, UserController.userID);
		getOrderDetailsItems.add(1, String.valueOf(OrderController.orderNumber));


		finalOrdersList = OrderController.ordersList; // **

		ObservableList<Order> data = FXCollections.observableArrayList(finalOrdersList);

		mealColumn.setCellValueFactory(new PropertyValueFactory<>("meal"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));

		SummaryTable.setItems(data);
	}

	
	/**
	 * Method to enable a button to use the refund just once if exists
	 * @param event - On Action
	 */
	@FXML
	void getUseBtn(ActionEvent event) {
		refundFlag = true;
		BtnUse.setDisable(true); // Use just once
		if (OrderController.totalPrice > Integer.valueOf(refundAmount)) {
			OrderController.totalPrice -= Integer.valueOf(refundAmount);
			this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));
			lblRefundAmount.setText("0");
			getRefundDetailsItems.add(2, "0");

		} else {
			int newRefund = Integer.valueOf(refundAmount) - OrderController.totalPrice;

			lblRefundAmount.setText(String.valueOf(newRefund));
			getRefundDetailsItems.add(2, String.valueOf(newRefund)); // String.valueOf(newRefund)
			OrderController.totalPrice = 0;
			this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));

		}
	}

	
	/**
	 * Method that adds the delivery details
	 * Adds the order details
	 * Sends an alert to the customer that his order got accepted and shows the next window 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getPayBtn(MouseEvent event) {
		boolean addOrderDetailsFlag = false;

		for (int i = 0; i < finalOrdersList.size(); i++) {
			boolean addOrderFlag = OrderController.addOrder(finalOrdersList.get(i));
			if (refundFlag) {
				boolean UpdateRefundDetailsFlag = OrderController.UpdateRefundDetails(getRefundDetailsItems);

			}
			if (addOrderFlag) {
				addOrderDetailsFlag = true;
			}

		}

		boolean addDeliveryDetailsFlag = OrderController.addDeliveryDetails(OrderController.deliveryList.get(0));
		System.out.println("addDeliveryDetails " + addDeliveryDetailsFlag);

		OrderController.addFlag = false;
		OrderController.mealNum = 0; // reset meals number for new order
		OrderController.totalPrice = 0; // reset for next order
		OrderController.ordersList.clear();
		OrderController.deliveryList.clear();

		if (addOrderDetailsFlag && addDeliveryDetailsFlag) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Order confirmation");
			alert.setHeaderText("Your order accepted");
			alert.setContentText("Now wait until the restaurant prepare it");
			alert.showAndWait();

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

	}

	/**
	 * Method take us back to the previous page
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	void getBackBtn(MouseEvent event) { 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDeliveryForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Delivery details");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that calculated the total price after discount
	 */
	private void getDiscount() {
		this.lblDiscount.setText("10%");
		OrderController.totalPrice *= 0.9;
		OrderController.discountFlag = true;
		OrderController.totalPriceForRefund = OrderController.totalPrice;
	}

}
