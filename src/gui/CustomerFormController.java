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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Customer;
import logic.Message;
import logic.MessageType;

public class CustomerFormController implements Initializable {

	ArrayList<Customer> customersList = new ArrayList<Customer>();

	@FXML
	private Button CreateNewOrder;
	@FXML
	private Button ViewExsistingOrder;
	@FXML
	private ImageView CreateNewOrderImage;
	@FXML
	private ImageView ViewExsistingOrderImage;
	@FXML
	private Rectangle CreateNewOrderShape;
	@FXML
	private Rectangle ViewExsistingOrderShape;

	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblStatus;

	/**
	 * Method that shows the status of the customer
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		lblWelcome.setText("Welcome, " + UserController.firstName + " " + UserController.lastName);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);
		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);
		else if (UserController.status.equals("Frozeen")) {
			lblStatus.setTextFill(Color.RED);
			CreateNewOrder.setDisable(true);
			CreateNewOrderImage.setDisable(true);
			CreateNewOrderShape.setDisable(true);
			
			ViewExsistingOrderImage.setDisable(true);
			ViewExsistingOrder.setDisable(true);
			ViewExsistingOrderShape.setDisable(true);
		}

	}

	
	/**
	 * Method that checks if the W4C is correct
	 * Gets the next window
	 * @param event - On Action
	 * @throws IOException
	 */
	@FXML
	void clickedCreateNewOrder(ActionEvent event) throws IOException {
		OrderController.totalPrice = 0;
		if (UserController.W4CScanFlag == false) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerW4CScanForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("W4C Scan");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerChooseRestaurantForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Choose Restaurant");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
	/**
	 * Method that loads the window of view existing order
	 * @param event - On Action
	 */
	@FXML
	void clickedViewExsistingOrder(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerViewOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Orders");
			UserController.currentStage.setTitle("Current orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * Method that checks if the W4C is correct
	 * Gets the next window
	 * Enable clicking on the icon
	 * @param event - Click On Mouse Event
	 * @throws IOException
	 */
	@FXML
	void clickedCreateNewOrderMouse(MouseEvent event) throws IOException {
		OrderController.totalPrice = 0;
		if (UserController.W4CScanFlag == false) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerW4CScanForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("W4C Scan");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerChooseRestaurantForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Choose Restaurant");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Method that loads the window of view existing order
	 * Enable clicking on the icon
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void clickedViewExsistingOrderMouse(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerViewOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to log out of the system and gets back to the LogIn window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getLogoutBtn(MouseEvent event) throws Exception {
		UserController.W4CScanFlag = false;

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

	public void start() {
		// TODO Auto-generated method stub

	}

}
