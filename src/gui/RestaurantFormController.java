package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.OrderController;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import logic.Message;
import logic.MessageType;
import logic.Order;

public class RestaurantFormController implements Initializable {

	@FXML
	private Button btnApproveOrders;
	@FXML
	private Button btnCreateMenu;
	@FXML
	private Button btnUpdateMenu;

	@FXML
	private ImageView approveOrdersImage;
	@FXML
	private ImageView createMenuImage;
	@FXML
	private ImageView updateMenuImage;
	@FXML
	private Rectangle approveOrdersShape;
	@FXML
	private Rectangle createMenuShape;
	@FXML
	private Rectangle updateMenuShape;

	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblTitle;

	@FXML
	private Label lblStatus;

	@FXML
	private ImageView imgApproveOrders;
	@FXML
	private ImageView imgCreateMenu;
	@FXML
	private ImageView imgUpdateMenu;

	ArrayList<Order> allOrders = new ArrayList<Order>();
	ArrayList<Order> ordersNotReady = new ArrayList<Order>();

	/**
	 * Method that displays the activities of the restaurant 
	 * Gets the number of hold orders
	 * Alerts the restaurant if there's a new order
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int newOrderExist = 0;
		String Key = UserController.firstName + "_" + UserController.address;
		System.out.println("Key is " + Key);
		lblTitle.setText("    " + UserController.firstName + " page");
		lblWelcome.setText("Welcome, " + UserController.firstName + " " + UserController.address);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);
		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);
		else if (UserController.status.equals("Frozeen")) {
			lblStatus.setTextFill(Color.RED);
			btnApproveOrders.setDisable(true);
			btnCreateMenu.setDisable(true);
			btnUpdateMenu.setDisable(true);
			approveOrdersImage.setDisable(true);
			createMenuImage.setDisable(true);
			updateMenuImage.setDisable(true);
			approveOrdersShape.setDisable(true);
			createMenuShape.setDisable(true);
			updateMenuShape.setDisable(true);
		}

		// get number of hold orders
		allOrders = OrderController.getAllOrderDetails(null);
		for (int i = 0; i < allOrders.size(); i++) {
			if (allOrders.get(i).getStatus().equals("Hold") && allOrders.get(i).getRestaurantName().equals(Key)) {
				newOrderExist++;
				ordersNotReady.add(allOrders.get(i));
			}
		}
		if (newOrderExist > 0) {
			System.out.println("newOrderExist is " + newOrderExist);
			
			 Alert alert = new Alert(AlertType.INFORMATION); 
		        alert.setTitle("New order exist");
		        alert.setHeaderText("You have new order received");
		        alert.showAndWait();
		}
	}

	public void start() {
	}

	
	/**
	 * Method that loads the next window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getApproveOrdersBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantApproveOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Approve orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the "Create Menu" window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getCreateMenuBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantCreateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Add meals");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the "Update Menu" window
	 * @param event
	 * @throws Exception
	 */
	public void getUpdateMenuBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantUpdateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Update Menu");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the "Approve Orders" window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getApproveOrdersBtnMouse(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantApproveOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Approve orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the "Create Menu" window by clicking on the icon
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getCreateMenuBtnMouse(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantCreateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Add meals");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the "Update Menu" window by clicking on the icon
	 * @param event
	 * @throws Exception
	 */
	public void getUpdateMenuBtnMouse(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantUpdateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Update Menu");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that loads the LogIn window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getLogoutBtn(MouseEvent event) throws Exception {
		UserController.logout(UserController.userName);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Login");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
