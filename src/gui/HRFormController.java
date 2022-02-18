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
 * HR manager page - main menu for HR. Contains two buttons 
 * for registration new employer and confirming business accounts.
 */
public class HRFormController implements Initializable {

	@FXML
	private Button btnRegister;
	@FXML
	private Button btnConfirm;

	@FXML
	private Button btnLogout;

	
	@FXML
	private Label lblStatus;

	@FXML
	private Label lblWelcome;

	/**
	 * Initializing HR stage
	 * @param arg0
	 * @param arg1
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
	 * Creating and opening Employer registration stage.
	 * The stage is saved in StageController
	 * @param event - On Action Event
	 * @throws Exception
	 */
	@FXML
	public void getRegisterBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HRRegisterEmployeeForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Register employee");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Creating and opening Business accounts confirming stage.
	 * @param event - on action event
	 * @throws Exception
	 */
	@FXML
	public void getConfirmBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HRConfirmAccountsForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Confirm business account");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method for backing to previous stage. Invoked by click on btnBack
	 * @param event - on action event
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
