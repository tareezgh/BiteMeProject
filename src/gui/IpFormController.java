package gui;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import client.BMClientUI;
import client.MainClient;
import client.ScreenControllers;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Message;
import logic.MessageType;

public class IpFormController {
	public String IpAddress;
	public String RecivedIP;
	@FXML
	private TextField txtIP;
	@FXML
	private Text txtMessage;
	
	@FXML
	private Label lblMessage;

	public String getIpAddress() {
		return txtIP.getText();
	}

	/**
	 * Method to loads the window
	 * @param primaryStage - primary screen
	 * @throws IOException
	 */
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IpForm.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		primaryStage.setTitle("IP Address");
		primaryStage.setScene(scene);

		primaryStage.show();
		ScreenControllers.ipFormController = loader.getController();
		UserController.currentStage = primaryStage;
	}

	
	/**
	 * Method that shows the IP of the host
	 * Warns the user if he  insert IP or if it wasn't found 
	 * Loads the next window
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getDoneBtn(MouseEvent event) {
		BMClientUI.mainClient.setHost(getIpAddress());
		RecivedIP = UserController.IpAddress(getIpAddress());
//		System.out.println("Recived IP " + RecivedIP);
		String ServerIP = MainClient.getServerIP();
//		System.out.println("Server IP: " + ServerIP);

		if (RecivedIP.trim().isEmpty()) { 
			txtIP.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 16));
			lblMessage.setText("You must insert IP Address.");
		} else if (RecivedIP.equals(ServerIP)) {
			lblMessage.setTextFill(Color.GREEN);
			lblMessage.setFont(new Font("Arial", 16));
			lblMessage.setText("IP Address found");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				ScreenControllers.loginFormController = loader.getController();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Login");
				UserController.currentStage.setScene(scene);
				ScreenControllers.loginFormController.start(UserController.currentStage);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			txtIP.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 16));
			lblMessage.setText("IP Address not found!");
		}

	}

	public void getExitBtn(MouseEvent event) throws Exception {
		System.exit(0);
	}

}
