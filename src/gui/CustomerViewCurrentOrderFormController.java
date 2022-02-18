package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;

public class CustomerViewCurrentOrderFormController implements Initializable {
	ArrayList<String> getItems = new ArrayList<String>(); // UserID,OrderNum , MealNum
	String selectedMeal;

	@FXML
	private Label lblTotalPrice;
	@FXML
	private Label lblMessage;
	@FXML
	private TableView<Order> OrderTbl = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> mealColumn;
	@FXML
	private TableColumn<Order, String> quantityColumn;
	@FXML
	private TableColumn<Order, String> lvlOfCookColumn;
	@FXML
	private TableColumn<Order, String> restrictionsColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;

	/**
	 * Method that shows the total price and display data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));
		this.lblTotalPrice.setTextFill(Color.BLACK);
		this.lblTotalPrice.setFont(new Font("Arial", 24));
		displayTable();
	}

	/**
	 * Method to get the selected meal and shows the customer which meal got
	 * selected
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getSelectedMeal(MouseEvent event) throws Exception {
		selectedMeal = OrderTbl.getSelectionModel().getSelectedItem().getMeal();
		lblMessage.setTextFill(Color.BLACK);
		lblMessage.setFont(new Font("Arial", 20));
		lblMessage.setText("You select " + selectedMeal);
	}

	/**
	 * Method that deletes a selected meal also updating the price and the quantity
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getDeleteBtn(MouseEvent event) throws Exception {
		int index = 0;
		int quantity = 0;
		if (selectedMeal != null) {

			for (int i = 0; i < OrderController.ordersList.size(); i++) {
				if (OrderController.ordersList.get(i).getMeal() == selectedMeal) {
					index = i;
					quantity = Integer.valueOf(OrderController.ordersList.get(i).getQuantity());
					System.out.println("quantity " + quantity);
				}
			}

			OrderController.totalPrice -= Integer.valueOf(OrderController.ordersList.get(index).getMealPrice());
			this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));

			if (quantity == 1) {

				OrderController.ordersList.remove(index);
				lblMessage.setText(selectedMeal + " deleted from your order");
			} else {
				quantity--;
				OrderController.ordersList.get(index).setQuantity(String.valueOf(quantity));
				lblMessage.setText("One " + selectedMeal + " deleted from your order");
			}

			lblMessage.setTextFill(Color.GREEN);
			lblMessage.setFont(new Font("Arial", 20));

			if (OrderController.ordersList.size() == 0) {
				OrderController.addFlag = false;
			}

		} else {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must choose meal first");
		}
		displayTable();
	}

	/**
	 * Method take us back to the previous page
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenuForm.fxml"));
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
	 * Method that shows the details of the order in a table
	 */
	public void displayTable() {
		ObservableList<Order> data = FXCollections.observableArrayList(OrderController.ordersList);
		mealColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMeal()));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		lvlOfCookColumn.setCellValueFactory(new PropertyValueFactory<>("lvlOfCook"));
		restrictionsColumn.setCellValueFactory(new PropertyValueFactory<>("restrictions"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));

		OrderTbl.setItems(data);
	}

}
