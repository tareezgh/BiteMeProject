package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.UserController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Order;

/**
 *	Controller for registration of employer by HR manager.
 *	The class get data Employer code and their Name from user
 *	and sends it to the server
 */
public class HRRegisterEmployeeFormController implements Initializable {

	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtName;
	@FXML
	private Label lblMessage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * Method gets a code of new Employer from txtCode field
	 * and a their name from txtName filed 
	 * and calls registerNewEmploeer with both parametrs.
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getSignUpBtn(MouseEvent event) throws Exception {
		boolean done = true;
		if (txtCode.getText().isEmpty()){
			done = false;
			txtCode.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		}else
			txtCode.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		
		if (txtName.getText().isEmpty()){
			done = false;
			txtName.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		}else
			txtName.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
				
		if (!done) { 
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must fill all fields");

		} else {

			if (UserController.registerNewEmployer(txtCode.getText(), txtName.getText())) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("Employer "+ txtName.getText() + " registration succseed");
				txtCode.setText(" ");
				txtName.setText(" ");
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("Employer "+ txtName.getText() + " registration failed");
			}
		}
	}

	/**
	 * The method for backing to previous stage. Invoked by click on btnBack
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HRForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("HR page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
