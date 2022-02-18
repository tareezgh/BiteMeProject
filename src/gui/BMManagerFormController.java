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

public class BMManagerFormController implements Initializable {
	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblStatus;
	@FXML
	private Button btnViewLogs;
	@FXML
	private Button btnManageData;
	@FXML
	private Button btnApprove;
	@FXML
	private Button btnRegister;
	@FXML
	private Button btnLogout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblWelcome.setText("Welcome, " + UserController.firstName + ", " + UserController.address);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);
		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);

	}

	public void start() {

	}

	/**
	 * Method take us to the ViewLogs page
	 * 
	 * @param event - On Action Event
	 * @throws Exception
	 */
	@FXML
	public void getViewLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerViewLogsForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("View logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method take us to the ManageData page
	 * 
	 * @param event - On Action Event
	 * @throws Exception
	 */
	@FXML
	public void getManageDataBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerManageDataForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Manage users data");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method take us to the Approve employee page
	 * 
	 * @param event - On Action Event
	 * @throws Exception
	 */
	@FXML
	public void getApproveBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerApproveRegistrationForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Approve employee");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method take us to the Registration page
	 * 
	 * @param event - On Action Event
	 * @throws Exception
	 */
	@FXML
	public void getRegisterBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerRegisterForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Register");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method take us back to the previous page
	 * 
	 * @param event - On Action Event
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
