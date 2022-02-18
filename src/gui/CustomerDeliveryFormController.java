package gui;

import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;

public class CustomerDeliveryFormController implements Initializable {

	private String AccountType;
	private String DeliveryTypeOptions;
	private String selectedDeliveryType;

	@FXML
	private Label lblMessage;
	@FXML
	private Label lblPayment;
	@FXML
	private Label lblName;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblAddress;
	@FXML
	private Label lblEmployeeName;
	@FXML
	private Label lblEmployeeCode;
	@FXML
	private TextField txtParticpated;
	@FXML
	private TextField txtName;
	@FXML
	private DatePicker txtDate;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtTimeHour;
	@FXML
	private TextField txtTimeMinute;
	@FXML
	private TextField txtAddress;
	@FXML
	private Label txtEmployeeName;
	@FXML
	private Label txtEmployeeCode;

	@FXML
	private ComboBox<String> cmpType;
	private ObservableList<String> Typelist;
	ArrayList<String> Type = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpDeliveryType;
	private ObservableList<String> DeliveryTypelist;
	ArrayList<String> DeliveryType = new ArrayList<String>();

	/**
	 * Method that sets the type of delivery in the comboBox Locks the past days in
	 * the calendar Shows employee name and code if the type of user is business
	 * Shows the payment method
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtEmployeeName.setFont(new Font("Arial", 17));
		txtEmployeeName.setText(UserController.employeeName);
		txtEmployeeCode.setFont(new Font("Arial", 17));
		txtEmployeeCode.setText(UserController.employeeCode);

		lblPayment.setFont(new Font("Arial", 20));
		lblPayment.setText(UserController.accountType + " account");
		AccountType = lblPayment.getText();
		OrderController.selectedTypeOfPayment = lblPayment.getText();
		System.out.println("lblPayment.getText(): " + lblPayment.getText());

		// Add Type comboBox
		Type.add("Take-Away");
		Type.add("Delivery");
		Typelist = FXCollections.observableArrayList(Type);
		cmpType.setItems(Typelist);

		cmpDeliveryType.setDisable(true);
		txtParticpated.setDisable(true);
		txtName.setDisable(true);
		txtDate.setDisable(true);
		txtPhone.setDisable(true);
		txtTimeHour.setDisable(true);
		txtTimeMinute.setDisable(true);
		txtAddress.setDisable(true);
		txtEmployeeName.setDisable(true);
		txtEmployeeCode.setDisable(true);

		// Lock past dates
		txtDate.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0);
			}
		});

	}

	/**
	 * Method that opens the required fields according to the type that got selected
	 */
	@FXML
	public void getSelectedType() {
		DeliveryTypeOptions = cmpType.getValue();
		OrderController.selectedType = DeliveryTypeOptions;

		if (DeliveryTypeOptions == "Take-Away") {
			lblMessage.setText(" ");
			resetFields();

			cmpDeliveryType.setDisable(true);
			txtName.setDisable(true);
			txtDate.setDisable(false);
			txtPhone.setDisable(true);
			txtTimeHour.setDisable(false);
			txtTimeMinute.setDisable(false);
			txtAddress.setDisable(true);

			lblName.setText("Name:");
			lblPhone.setText("Phone:");
			lblAddress.setText("Address:");

		} else if (DeliveryTypeOptions == "Delivery") {
			lblMessage.setText(" ");

			resetFields();

			cmpDeliveryType.setDisable(false);
			txtName.setDisable(false);
			txtDate.setDisable(false);
			txtPhone.setDisable(false);
			txtTimeHour.setDisable(false);
			txtTimeMinute.setDisable(false);
			txtAddress.setDisable(false);

			lblName.setText("Name*:");
			lblPhone.setText("Phone*:");
			lblAddress.setText("Address*:");

		}

		setComboBox();

	}

	/**
	 * Method to get the selected delivery type
	 */
	@FXML
	public void getSelectedDileveryType() {
		lblMessage.setText(" ");
		cmpDeliveryType.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtTimeHour.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtTimeMinute.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtDate.setStyle(" -fx-background-color: #fff; -fx-border-color: black");
		txtName.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtPhone.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtAddress.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtParticpated.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		selectedDeliveryType = cmpDeliveryType.getValue();
		if (selectedDeliveryType.equals("Shared")) {
			OrderController.sharedFlag = true;
			txtParticpated.setDisable(false);

		} else {

			OrderController.sharedFlag = false;
			txtParticpated.setDisable(true);
		}

	}

	/**
	 * Method that gets the next window Make sure that every customer has at least
	 * one meal if the type of delivery is shared Checks if the customer filled all
	 * the required field according to the type of delivery and if its
	 * private/business
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getNextBtn(MouseEvent event) {
		Delivery delivery;
		boolean next = false;
		boolean Continue = true;

		if (OrderController.sharedFlag) {
			OrderController.particepationNumber = txtParticpated.getText();
			if (!OrderController.particepationNumber.isEmpty()) {
				if (Integer.valueOf(OrderController.particepationNumber) > OrderController.ordersList.size()) {
					txtParticpated.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
					lblMessage.setFont(new Font("Arial", 18));
					lblMessage.setText("You must insert one meal at least for each person");
					lblMessage.setTextFill(Color.RED);
					Continue = false;

				}
			} else {
				checkValidFileds();
				txtParticpated.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("You must insert particpation number");
				lblMessage.setTextFill(Color.RED);
			}

		} else
			txtParticpated.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		if (Continue) {
			if (DeliveryTypeOptions == "Take-Away") {
				if (txtTimeHour.getText().isEmpty() || txtTimeMinute.getText().isEmpty()
						|| txtDate.getValue() == null) {
					checkValidFileds();
					next = false;
				} else
					next = true;

			} else if (DeliveryTypeOptions == "Delivery") {
				OrderController.selecteDeliveryType = cmpDeliveryType.getValue();

				switch (AccountType) {
				case "Private account":

					if (txtTimeHour.getText().isEmpty() || txtTimeMinute.getText().isEmpty()
							|| txtDate.getValue() == null || txtName.getText().isEmpty() || txtPhone.getText().isEmpty()
							|| txtAddress.getText().isEmpty()) {
						checkValidFileds();

						next = false;
					} else {

						next = true;
					}
					break;

				case "Business account":
					if (txtTimeHour.getText().isEmpty() || txtTimeMinute.getText().isEmpty()
							|| txtDate.getValue() == null || txtName.getText().isEmpty() || txtPhone.getText().isEmpty()
							|| txtAddress.getText().isEmpty() || txtEmployeeName.getText().isEmpty()
							|| txtEmployeeCode.getText().isEmpty()) {
						checkValidFileds();

						next = false;
					} else {
						next = true;
					}

					break;

				default:
					next = false;
					break;
				}
			}

			if (next) {
				OrderController.selectedTime = txtTimeHour.getText() + ":" + txtTimeMinute.getText();
				OrderController.selectedDate = txtDate.getValue().toString();
				if (DeliveryTypeOptions == "Delivery") {

					delivery = new Delivery(String.valueOf(OrderController.deliveryNum), UserController.userID,
							String.valueOf(OrderController.orderNumber), AccountType, DeliveryTypeOptions,
							OrderController.selectedTime, txtDate.getValue().toString(), txtName.getText(),
							txtPhone.getText(), txtAddress.getText(), txtEmployeeName.getText(),
							txtEmployeeCode.getText(), "1", "1", "1");
					delivery.setOrderNum(OrderController.orderNumber);

				} else {

					delivery = new Delivery(String.valueOf(OrderController.deliveryNum), UserController.userID,
							String.valueOf(OrderController.orderNumber), AccountType, DeliveryTypeOptions,
							OrderController.selectedTime, txtDate.getValue().toString());
					delivery.setOrderNum(OrderController.orderNumber);

				}

				OrderController.deliveryNum++;
				OrderController.deliveryList.add(0, delivery);

				FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerFinalOrderForm.fxml"));
				Parent root;
				try {
					root = loader.load();
					Scene scene = new Scene(root);
					UserController.currentStage.setTitle("Final order page");
					UserController.currentStage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				checkValidFileds();
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("You must fill all the fields");
				lblMessage.setTextFill(Color.RED);
			}
		}
	}

	/**
	 * Method take us back to the previous page
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	void getBackBtn(MouseEvent event) {
		OrderController.sharedFlag = false;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Menu");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to set data in the comboBox
	 */
	private void setComboBox() {

		if (AccountType.equals("Private account")) {
			txtEmployeeName.setDisable(true);
			txtEmployeeCode.setDisable(true);
			DeliveryType.clear();
			DeliveryType.add("Regular");
			DeliveryType.add("Robot");

			lblEmployeeName.setText("Employee Name:");
			lblEmployeeCode.setText("Employee Code:");

		} else if (AccountType.equals("Business account")) {
			txtEmployeeName.setDisable(false);
			txtEmployeeCode.setDisable(false);
			DeliveryType.clear();
			DeliveryType.add("Regular");
			DeliveryType.add("Shared");
			DeliveryType.add("Robot");

			lblEmployeeName.setText("Employee Name*:");
			lblEmployeeCode.setText("Employee Code*:");
		}

		DeliveryTypelist = FXCollections.observableArrayList(DeliveryType);
		cmpDeliveryType.setItems(DeliveryTypelist);
	}

	/**
	 * Method that checks that the user filled all the field
	 */
	private void checkValidFileds() {
		if (selectedDeliveryType == null && DeliveryTypeOptions == "Delivery")
			cmpDeliveryType.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			cmpDeliveryType.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		if (txtTimeHour.getText().isEmpty())
			txtTimeHour.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtTimeHour.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		if (txtTimeMinute.getText().isEmpty())
			txtTimeMinute.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtTimeMinute.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		if (txtDate.getValue() == null)
			txtDate.setStyle(" -fx-background-color: #fff; -fx-border-color: red");
		else
			txtDate.setStyle(" -fx-background-color: #fff; -fx-border-color: black");

		if (DeliveryTypeOptions == "Delivery") {

			if (txtName.getText().isEmpty())
				txtName.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			else
				txtName.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
			if (txtPhone.getText().isEmpty())
				txtPhone.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			else
				txtPhone.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

			if (txtAddress.getText().isEmpty())
				txtAddress.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			else
				txtAddress.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		}

	}

	/**
	 * Method to reset all the fields
	 */
	private void resetFields() {
		cmpDeliveryType.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtTimeHour.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtTimeMinute.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtDate.setStyle(" -fx-background-color: #fff; -fx-border-color: black");
		txtName.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtPhone.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtAddress.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtParticpated.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
	}

}
