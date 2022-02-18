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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.Restaurant;

public class CustomerChooseRestaurantFormController implements Initializable {
	ArrayList<Restaurant> AllRestaurants = new ArrayList<Restaurant>();
	ArrayList<Restaurant> NearRestaurants = new ArrayList<Restaurant>();
	RadioButton selectedRadioButton;
	private String selectedRestaurantName;
	private String selectedRestaurantBranch;

	private ToggleGroup group = new ToggleGroup();

	ObservableList<Restaurant> data;

	@FXML
	private Label lblMessage;
	@FXML
	private RadioButton HomeBranch;
	@FXML
	private RadioButton AllBranches;
	@FXML
	private TableView<Restaurant> tbl = new TableView<Restaurant>();
	@FXML
	private TableColumn<Restaurant, String> nameColumn;
	@FXML
	private TableColumn<Restaurant, String> addressColumn;
	@FXML
	private TableColumn<Restaurant, String> BMMenagerIDColumn;

	/**
	 * Method that initialize the radio button
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		OrderController.discountFlag = false;
		AllRestaurants = OrderController.getAllRestaurants(null);
		// Radio 1:
		HomeBranch = new RadioButton("Home branch");
		HomeBranch.setToggleGroup(group);
		// Radio 2:
		AllBranches = new RadioButton("All branches");
		AllBranches.setToggleGroup(group);

	}

	/**
	 * Method that setting the selected radio button true
	 * 
	 * @param event - On Action event
	 */
	@FXML
	public void getSelectedRadioButton(ActionEvent event) {

		if (!HomeBranch.isSelected()) {
			HomeBranch.setSelected(true);
			AllBranches.setSelected(false);
		} else {
			HomeBranch.setSelected(false);
			AllBranches.setSelected(true);
		}

		displayTable();
		NearRestaurants.clear(); // reset ArrayList
	}

	/**
	 * Method to select restaurant and shows the user which one selected
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	public void getSelectedRestaurant(MouseEvent event) {
		selectedRestaurantName = tbl.getSelectionModel().getSelectedItem().getRestaurantName();
		selectedRestaurantBranch = tbl.getSelectionModel().getSelectedItem().getRestaurantBranch();

		lblMessage.setTextFill(Color.BLACK);
		lblMessage.setFont(new Font("Arial", 18));

		lblMessage.setText("You select " + selectedRestaurantName + " restaurant");

	}

	/**
	 * Method that opens the new window and shows the menu If the user clicks next
	 * without choosing a restaurant the system shows a warning
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getNextBtn(MouseEvent event) {

		if (selectedRestaurantName != null) {

			OrderController.ordersList.clear();
			OrderController.deliveryList.clear();
			OrderController.selectedRestaurantName = selectedRestaurantName; // to save
			OrderController.selectedRestaurantBranch = selectedRestaurantBranch;
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
		} else {
			lblMessage.setText("You must choose restaurant");
			lblMessage.setTextFill(Color.RED);
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
	 * Method that shows the restaurants' names based on what the user chose( Home
	 * branch or All branches)
	 */
	private void displayTable() {
		if (AllBranches.isSelected()) {
			data = FXCollections.observableArrayList(AllRestaurants);
		} else {
			for (int i = 0; i < AllRestaurants.size(); i++) {
				if (AllRestaurants.get(i).getRestaurantBranch().equals(UserController.address)) {
					NearRestaurants.add(AllRestaurants.get(i));
				}
			}
			data = FXCollections.observableArrayList(NearRestaurants);
		}

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantBranch"));
		tbl.setItems(data);

	}

}
