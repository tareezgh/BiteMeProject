package gui;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.MainClient;
import client.ScreenControllers;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Message;
import logic.MessageType;

public class CustomerW4CScanFormController implements Initializable {
	@FXML
	private TextField txtW4CCode;
	@FXML
	private Label lblMessage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	/**
	 * Method that checks if the W4C code is correct, if yes then loads the next window else
	 * warns the customer that the W4C is wrong
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getDoneBtn(MouseEvent event) {
		String W4cCode = txtW4CCode.getText();
		if (W4cCode.equals(UserController.w4c)) {
			UserController.W4CScanFlag = true;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerChooseRestaurantForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Choose Restaurant");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(W4cCode.isEmpty()){
			txtW4CCode.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must insert W4C code");
		}else {
			txtW4CCode.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("W4C code is wrong");
		}

	}

	/**
	 * Method take us back to the previous page
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
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
