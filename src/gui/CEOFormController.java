package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Message;
import logic.MessageType;

/**
 * This class defines the Controller for CEO page
 *
 */
public class CEOFormController implements Initializable {

	@FXML
	private Label lblWelcome;
	@FXML
	private Button btnCompare;
	@FXML
	private Label lblName;
	@FXML
	private Label lblStatus;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnViewIncomeLogs;
	@FXML
	private Button btnViewOrderLogs;
	@FXML
	private Button btnViewPerformanceLogs;

	/**
	 * Initialize labels
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblWelcome.setText("Welcome, " + UserController.firstName + " " + UserController.lastName);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);
		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);

	}

	public void start() {

	}

	/**
	 * This function defines the next window of income logs for CEO when clicking
	 * the button
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getViewIncomeLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOViewLogsIncomeForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Income logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defins the button to show next page of order logs
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getViewOrderLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOViewLogsOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Order logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defins the button to show next page of performance logs
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getViewPerformanceLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOViewLogsPerformanceForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Performance logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defins the button to show next page of compare logs
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getCompareBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOCompareLogsForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Compare logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defines the button to logout the system and show Login page
	 * 
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
