package gui;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Customer;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;
import logic.UserManagment;

public class BMManagerManageDataFormController implements Initializable {
	ArrayList<String> getUserItems = new ArrayList<String>(); // CustomerID, status to be change
	
	ArrayList<Customer> customersList = new ArrayList<Customer>();
	ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
	ArrayList<UserManagment> usersList = new ArrayList<UserManagment>();

	private String selectedCustomer;
	private String selectedAuthorization;
	private String selectedStatus;
	private String selectedNewStatus;

	@FXML
	private Label lblMessage;

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> statusList;
	ArrayList<String> statusArray = new ArrayList<String>();

	@FXML
	private TableView<UserManagment> UsersTbl = new TableView<UserManagment>();
	@FXML
	private TableColumn<UserManagment, String> IDColumn;
	@FXML
	private TableColumn<UserManagment, String> userNameColumn;
	@FXML
	private TableColumn<UserManagment, String> passwordColumn;
	@FXML
	private TableColumn<UserManagment, String> firstNameColumn;
	@FXML
	private TableColumn<UserManagment, String> addressColumn;
	@FXML
	private TableColumn<UserManagment, String> autorizationColumn;
	@FXML
	private TableColumn<UserManagment, String> currentStatusColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		cmpStatus.setDisable(true);
		displayTable();

	}

	/**
	 * Method get the selected Customer from table with the data
	 */
	@FXML
	public void getSelectedCustomer() {
		cmpStatus.setDisable(false);
		getUserItems.add(0, UsersTbl.getSelectionModel().getSelectedItem().getiD());
		selectedCustomer = UsersTbl.getSelectionModel().getSelectedItem().getUserName();
		selectedAuthorization = UsersTbl.getSelectionModel().getSelectedItem().getAuthorization();
		selectedStatus = UsersTbl.getSelectionModel().getSelectedItem().getStatus();
		lblMessage.setTextFill(Color.BLACK);
		lblMessage.setFont(new Font("Arial", 20));

		lblMessage.setText("You select " + selectedCustomer + " and his status is " + selectedStatus);

	}

	/**
	 * Method get the selected status from comboBox
	 */
	@FXML
	public void getSelectedStatus() {
		selectedNewStatus = cmpStatus.getValue();
		getUserItems.add(1, selectedNewStatus); // save new status

	}

	/**
	 * Method change the status after select the customer and the status
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getChangeBtn(MouseEvent event) throws Exception {
		boolean changed = false;

		if (selectedNewStatus != null) {

			if (selectedCustomer == null) {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("You must select customer first");
			} else if (selectedStatus.equals(selectedNewStatus)) {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("The current status is " + selectedNewStatus);
			} else {
				usersList.clear();

				if (selectedAuthorization.equals("Customer"))
					changed = UserController.changeCustomerStatus(getUserItems);
				else if (selectedAuthorization.equals("Restaurant"))
					changed = UserController.changeRestaurantStatus(getUserItems);
				else {
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("Select user please");
				}
				this.UsersTbl.setEditable(true);

				cmpStatus.setDisable(true);
				if (changed) {
					lblMessage.setTextFill(Color.GREEN);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("Status of " + selectedCustomer + " changed to " + selectedNewStatus);
				} else {
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("Status not changed");
				}
				cmpStatus.setValue("");
				displayTable(); // display table again

			}
		} else {
			cmpStatus.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must select status first");
		}

	}

	/**
	 * Method take us back to the previous page
	 * 
	 * @param event - Click On Mouse Event
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
	 * Method set the values into the comboBox
	 */
	private void setComboBox() {
		// Add category comboBox
		statusArray.add("Active");
		statusArray.add("Frozeen");
		statusList = FXCollections.observableArrayList(statusArray);
		cmpStatus.setItems(statusList);
	}

	/**
	 * Methods that get details of restaurant and customers and sort in one
	 * ArrayList
	 * @return ObservableList<UserManagment>
	 */
	private ObservableList<UserManagment> getUsers() {
		customersList = UserController.getAllCustomerDetails("Customer");
		restaurantsList = UserController.getAllRestaurantsDetails("Restaurant");

		for (int i = 0; i < customersList.size(); i++) {
			UserManagment customer = new UserManagment(customersList.get(i).getCustomerID(),
					customersList.get(i).getUserName(), customersList.get(i).getPassword(),
					customersList.get(i).getFirstName(), customersList.get(i).getLastName(),
					customersList.get(i).getAddress(), customersList.get(i).getEmail(), customersList.get(i).getPhone(),
					customersList.get(i).getAuthorization(), customersList.get(i).getStatus());
			usersList.add(customer);
		}
		for (int i = 0; i < restaurantsList.size(); i++) {
			UserManagment restaurant = new UserManagment(restaurantsList.get(i).getRestaurantID(),
					restaurantsList.get(i).getUserName(), restaurantsList.get(i).getPassword(),
					restaurantsList.get(i).getRestaurantName(), restaurantsList.get(i).getRestaurantBranch(),
					restaurantsList.get(i).getRestaurantBranch(), restaurantsList.get(i).getEmail(),
					restaurantsList.get(i).getPhone(), restaurantsList.get(i).getAuthorizations(),
					restaurantsList.get(i).getRestaurantStatus());
			usersList.add(restaurant);
		}

		ObservableList<UserManagment> data = FXCollections.observableArrayList(usersList);
		return data;
	}

	/**
	 * Method insert the data into the table
	 */
	private void displayTable() {
		IDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getiD()));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		autorizationColumn.setCellValueFactory(new PropertyValueFactory<>("authorization"));
		currentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		ObservableList<UserManagment> data = FXCollections.observableArrayList(getUsers());
		UsersTbl.setItems(data);
	}

}
