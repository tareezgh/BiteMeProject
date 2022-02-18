package gui;

import java.io.IOException;

import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Customer;
import logic.Employer;
import logic.Order;
import logic.Restaurant;

public class BMManagerRegisterFormController implements Initializable {
	String selectedAccountType;
	String selectedIDForCustomerOrRestaurant;
	String selectedAuthorization;
	String selectedID;
	String selectedEmployeeName;
	String selectedEmployeeCode;

	String userID;

	boolean allFieldsFill = true;

	boolean txtCreditCardFill = false;
	boolean cmpEmployeeNameFill = false;

	public static ArrayList<Customer> customersDetailsAsUsers;
	public static ArrayList<Restaurant> restautantsDetailsAsUsers;
	public static ArrayList<Employer> employeeList;

	@FXML
	private ComboBox<String> cmpIDForCustomerAndRestaurant;
	ArrayList<String> iDForCustomerAndRestaurant = new ArrayList<String>();
	private ObservableList<String> iDForCustomerAndRestaurantlist;

	@FXML
	private ComboBox<String> cmpAccountType;
	ArrayList<String> AccountType = new ArrayList<String>();
	private ObservableList<String> AccountTypelist;

	@FXML
	private ComboBox<String> cmpEmployeeName;
	ArrayList<String> EmployeeName = new ArrayList<String>();
	private ObservableList<String> EmployeeNamelist;

	@FXML
	private Label txtID;
	@FXML
	private Label txtFirstName;
	@FXML
	private Label txtLastName;
	@FXML
	private Label txtPhoneNumber;
	@FXML
	private Label txtEmail;
	@FXML
	private TextField txtCreditCard;

	@FXML
	private Label txtEmployeeCode;
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblPersonal;
	@FXML
	private Label lblAdditional;
	@FXML
	private Label lblIDNumber;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastNameOrAddress;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblAccountType;
	@FXML
	private Label lblCreditCard;
	@FXML
	private Label lblEmployeeName;
	@FXML
	private Label lblEmployeeCode2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setData();

		// Add Account type combo box
		AccountType.add("Private account");
		AccountType.add("Business account");
		AccountTypelist = FXCollections.observableArrayList(AccountType);
		cmpAccountType.setItems(AccountTypelist);

		txtCreditCard.setDisable(true);
		cmpEmployeeName.setDisable(true);

		setEmployeeCodeComboBox();

		lblPersonal.setStyle("-fx-font-weight: bold");
		lblAdditional.setStyle("-fx-font-weight: bold");

		lblIDNumber.setStyle("-fx-font-weight: bold");
		lblFirstName.setStyle("-fx-font-weight: bold");
		lblLastNameOrAddress.setStyle("-fx-font-weight: bold");
		lblPhone.setStyle("-fx-font-weight: bold");
		lblEmail.setStyle("-fx-font-weight: bold");

		lblAccountType.setStyle("-fx-font-weight: bold");
		lblCreditCard.setStyle("-fx-font-weight: bold");
		lblEmployeeName.setStyle("-fx-font-weight: bold");
		lblEmployeeCode2.setStyle("-fx-font-weight: bold");

	}

	

	/**
	 * Method set Employee Code into the comboBox
	 */
	public void setEmployeeCodeComboBox() {
		employeeList = UserController.getEmployeeDetails("Approve");
		for (int i = 0; i < employeeList.size(); i++) {

			EmployeeName.add(employeeList.get(i).getEmployerName());

		}
		EmployeeNamelist = FXCollections.observableArrayList(EmployeeName);
		cmpEmployeeName.setItems(EmployeeNamelist);
	}

	/**
	 * Method get ID and Order number from comboBox
	 */
	@FXML
	public void getSelectedIDForCustomerAndRestaurant() {
		lblMessage.setText(" ");
		cmpAccountType.setValue("");
		txtCreditCard.setText("");
		cmpEmployeeName.setValue("");
		txtEmployeeCode.setText("");

		cmpIDForCustomerAndRestaurant.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtCreditCard.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		selectedIDForCustomerOrRestaurant = cmpIDForCustomerAndRestaurant.getValue();
		if (selectedIDForCustomerOrRestaurant != null) {
			String[] details = selectedIDForCustomerOrRestaurant.split("_");
			selectedID = details[0];
			selectedAuthorization = details[1];

			switch (selectedAuthorization) {
			case "Customer": /// ***
				lblAccountType.setText("Account Type*:");
				lblCreditCard.setText("Credit Card*:");
				lblEmployeeName.setText("Employee Name:");
				lblEmployeeCode2.setText("Employee Code:");
				cmpAccountType.setDisable(false);
				txtCreditCard.setDisable(false);
				resetFiledsColor();
				lblLastNameOrAddress.setText("Last Name:");
				for (int i = 0; i < customersDetailsAsUsers.size(); i++) {
					if (customersDetailsAsUsers.get(i).getCustomerID().equals(selectedID)) {
						txtID.setText(customersDetailsAsUsers.get(i).getCustomerID());
						txtFirstName.setText(customersDetailsAsUsers.get(i).getFirstName());
						txtLastName.setText(customersDetailsAsUsers.get(i).getLastName());
						txtPhoneNumber.setText(customersDetailsAsUsers.get(i).getPhone());
						txtEmail.setText(customersDetailsAsUsers.get(i).getEmail());
					}
				}

				break;
			case "Restaurant":
				lblAccountType.setText("Account Type:");
				lblCreditCard.setText("Credit Card:");
				lblEmployeeName.setText("Employee Name*:");
				lblEmployeeCode2.setText("Employee Code*:");
				resetFiledsColor();

				cmpAccountType.setDisable(true);
				txtCreditCard.setDisable(true);
				cmpEmployeeName.setDisable(false);
				lblLastNameOrAddress.setText("Address:");
				for (int i = 0; i < restautantsDetailsAsUsers.size(); i++) {
					if (restautantsDetailsAsUsers.get(i).getRestaurantID().equals(selectedID)) {
						txtID.setText(restautantsDetailsAsUsers.get(i).getRestaurantID());
						txtFirstName.setText(restautantsDetailsAsUsers.get(i).getRestaurantName());
						txtLastName.setText(restautantsDetailsAsUsers.get(i).getRestaurantBranch());
						txtPhoneNumber.setText(restautantsDetailsAsUsers.get(i).getPhone());
						txtEmail.setText(restautantsDetailsAsUsers.get(i).getEmail());
					}
				}
				break;
			}
		}

	}

	

	/**
	 * Method get the account type from comboBox and open the right comboBox for
	 * each one
	 */
	@FXML
	public void getSelectedAccountType() {
		cmpAccountType.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtCreditCard.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		selectedAccountType = cmpAccountType.getValue();
		switch (selectedAccountType) {
		case "Private account":
			cmpEmployeeName.setDisable(true);
			break;
		case "Business account":
			cmpEmployeeName.setDisable(false);

			break;
		}

	}

	/**
	 * Method get the selected Employee name from comboBox and put his code into
	 * employee code
	 */
	@FXML
	public void getSelectedEmployeeName() {
		cmpEmployeeName.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		selectedEmployeeName = cmpEmployeeName.getValue();
		for (int i = 0; i < employeeList.size(); i++) {

			if (employeeList.get(i).getEmployerName().equals(selectedEmployeeName)) {
				selectedEmployeeCode = employeeList.get(i).getEmployerCode();
				txtEmployeeCode.setText(selectedEmployeeCode);
			}

		}
	}

	/**
	 * method check if all fields are right and then import the data to the database
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getSignUpBtn(MouseEvent event) throws Exception {
		boolean change = false;
		boolean allFields = true;
		ArrayList<String> customerItems = new ArrayList<String>(); // userID, creditCard, status
		ArrayList<String> restaurantItems = new ArrayList<String>(); // userID, creditCard, status
		userID = txtID.getText();
		restaurantItems.add(0, userID);

		customerItems.add(0, userID);
		customerItems.add(1, txtCreditCard.getText());

		if (selectedIDForCustomerOrRestaurant != null) {

			switch (selectedAuthorization) {
			case "Customer":
				if (!cmpAccountType.getValue().isEmpty()) {
					if (selectedAccountType.equals("Private account")) {
						if (txtCreditCard.getText().isEmpty()) {
							allFields = false;
							txtCreditCard.setStyle(
									"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
							lblMessage.setTextFill(Color.RED);
							lblMessage.setFont(new Font("Arial", 20));
							lblMessage.setText("You must fill credit card number");
						} else {
							txtCreditCard.setStyle(
									"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
							if (allFields)
								change = UserController.updatePrivateAccount(customerItems);
						}
					} else if (selectedAccountType.equals("Business account")) {
						if (txtCreditCard.getText().isEmpty()) {
							allFields = false;
							txtCreditCard.setStyle(
									"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
						} else {
							txtCreditCard.setStyle(
									"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
						}

						if (cmpEmployeeName.getValue().isEmpty()) {
							allFields = false;
							cmpEmployeeName.setStyle(
									"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
							lblMessage.setTextFill(Color.RED);
							lblMessage.setFont(new Font("Arial", 20));
							lblMessage.setText("You must fill all field");
						} else {
							customerItems.add(2, "Requested");
							customerItems.add(3, selectedEmployeeName);
							customerItems.add(4, selectedEmployeeCode);
							if (allFields)
								change = UserController.changeCustomerAuthorization(customerItems);

						}

					}
				} else {
					allFields = false;
					cmpAccountType.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("You must choose account type");

				}

				break;

			case "Restaurant":
				if (cmpEmployeeName.getValue().isEmpty()) {
					allFields = false;
					cmpEmployeeName.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("You must choose employee ");
				} else {
					restaurantItems.add(1, selectedEmployeeName);
					restaurantItems.add(2, selectedEmployeeCode);
					if (allFields)
						change = UserController.changeRestaurantAuthorization(restaurantItems);

				}
				break;

			}

			if (change) {

				iDForCustomerAndRestaurant.clear();
				setData();
				cmpAccountType.setValue("");

				txtCreditCard.setText("");
				cmpEmployeeName.setValue("");
				txtEmployeeCode.setText("");

				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Authorization of changed");
			}
		} else {

			cmpIDForCustomerAndRestaurant.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must choose user first");
		}


	}

	/**
	 * Method take us back to the previous page
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("BM Manager page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method set the Customer/Restaurant into the comboBox
	 */
	private void setData() {
		customersDetailsAsUsers = UserController.getCustomersDetailsAsUsers();
		restautantsDetailsAsUsers = UserController.getRestaurantDetailsAsUsers();

		for (int i = 0; i < customersDetailsAsUsers.size(); i++) {
			if (customersDetailsAsUsers.get(i).getAuthorization().equals("User")) {
				iDForCustomerAndRestaurant.add(customersDetailsAsUsers.get(i).getCustomerID() + "_Customer");
			}
		}

		for (int i = 0; i < restautantsDetailsAsUsers.size(); i++) {
			if (restautantsDetailsAsUsers.get(i).getAuthorizations().equals("User")) {
				iDForCustomerAndRestaurant.add(restautantsDetailsAsUsers.get(i).getRestaurantID() + "_Restaurant");
			}
		}

		iDForCustomerAndRestaurantlist = FXCollections.observableArrayList(iDForCustomerAndRestaurant);
		cmpIDForCustomerAndRestaurant.setItems(iDForCustomerAndRestaurantlist);

	}
	
	private void resetFiledsColor() {
		cmpAccountType.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtCreditCard.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		cmpEmployeeName.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

	}

}
