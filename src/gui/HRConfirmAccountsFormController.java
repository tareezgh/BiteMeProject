package gui;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.UserController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import logic.Customer;


/**
 * This class defines the Controller for approving account by HR
 * 
 */
public class HRConfirmAccountsFormController implements Initializable {

	ArrayList<String> userItems = new ArrayList<String>(); // ID, status to be change

	private String selectedUserID;
	private String selectedStatus;

	@FXML
	private Label lblMessage;
	@FXML
	private TextField txtBilling;

	@FXML
	private TableView<Customer> tbl = new TableView<Customer>();
	@FXML
	private TableColumn<Customer, String> idCol;
	@FXML
	private TableColumn<Customer, String> firstNameCol;
	@FXML
	private TableColumn<Customer, String> lastNameCol;

	@FXML
	private TableColumn<Customer, String> phoneCol;
	@FXML
	private TableColumn<Customer, String> emailCol;
	@FXML
	private TableColumn<Customer, String> employeeNameCol;
	@FXML
	private TableColumn<Customer, String> companyCodeCol;
	@FXML
	private TableColumn<Customer, String> auturizationCol;

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> StatusList;
	ArrayList<String> Status = new ArrayList<String>();

	/**
	 * This method initialize all sataus and combo boxes as well as relevant labels
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayTable();

		// set comboBox
		Status.add("Approve");
		Status.add("Decline");
		StatusList = FXCollections.observableArrayList(Status);
		cmpStatus.setItems(StatusList);
		cmpStatus.setDisable(true);
		txtBilling.setDisable(true);
	}

	/**
	 * The function displays table of account for confirmation by HR
	 */
	public void displayTable() {
		ArrayList<Customer> customersList = UserController.getAllRequstionForBuisnesAccount();
		ObservableList<Customer> data = FXCollections.observableArrayList(customersList);

		idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerID()));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		employeeNameCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		companyCodeCol.setCellValueFactory(new PropertyValueFactory<>("employeeCode"));
		auturizationCol.setCellValueFactory(new PropertyValueFactory<>("authorization"));
		tbl.getItems().setAll(data);
	}

	/**
	 * This method define the act of choosing an account to approve
	 * 
	 * @param event
	 */
	@FXML
	public void getSelectedUserRequest(MouseEvent event) {
		cmpStatus.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		cmpStatus.setDisable(false);
		Customer customer = tbl.getSelectionModel().getSelectedItem();
		if (customer != null) {
			selectedUserID = customer.getCustomerID();
			userItems.add(0, selectedUserID);
		}

	}

	/**
	 * This function defines the action of choosing the new status of ComboBox in HR
	 * confirmation window
	 */
	@FXML
	public void getSelectedStatus() {
		cmpStatus.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtBilling.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		selectedStatus = cmpStatus.getValue();
		userItems.add(1, selectedStatus);
		if (selectedStatus.equals("Approve")) {
			txtBilling.setDisable(false);
		} else {
			txtBilling.setText("");
			txtBilling.setDisable(true);
		}

	}

	/**
	 * This function defines the button of confirmation for the new status of the
	 * account It defines relevent information of lables
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getConfirmBtn(MouseEvent event) throws Exception {
		if (cmpStatus.getValue() != null) {
			if (txtBilling.getText().isEmpty() && selectedStatus.equals("Approve")) {
				txtBilling.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("You have to insert billing amount");
			} else {
				lblMessage.setText(" ");
				userItems.add(2, txtBilling.getText());
				if (UserController.confirmBuisnesAccount(userItems)) {
					if (selectedStatus == "Approve") {
						lblMessage.setTextFill(Color.GREEN);
						lblMessage.setFont(new Font("Arial", 20));
						lblMessage.setText(selectedUserID + " has been approved");
					} else {
						lblMessage.setTextFill(Color.RED);
						lblMessage.setFont(new Font("Arial", 20));
						lblMessage.setText(selectedUserID + " has been decliened");
					}

				} else {
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("Error status not changed");
				}

				cmpStatus.setValue(" ");

				displayTable();
			}
		} else {
			cmpStatus.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		}

	}

	/**
	 * This function defines the button getting back to HR main screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HRForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("HR page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
