package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.BMClientUI;
import client.ScreenControllers;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Message;
import logic.MessageType;

public class LoginFormController {
	public static String username;
	@FXML
	private Label lblMessage;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;

	public String getUsername() {
		return txtUsername.getText();
	}

	private String getPassword() {
		return txtPassword.getText();
	}

	public void start(Stage primaryStage) throws IOException {

	}

	/**
	 * Method that checks if the user exist and if the user is already connected
	 * Checks if the password and the user name is correct and if the user filled
	 * all the fields Loads the next window according to the type of the user
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getLoginBtn(MouseEvent event) {
		ArrayList<String> items = new ArrayList<String>();

		boolean usersCheckExistFlag = UserController.usersCheckExist();

		if (!usersCheckExistFlag) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Users does not exist");
			alert.setHeaderText("Users does not exist");
			alert.setContentText("Wait until imporort");
			alert.showAndWait();
			System.out.println("usersCheckExistFlag " + usersCheckExistFlag);
		} else {
			txtUsername.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
			txtPassword.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
			UserController.getUserDetails(getUsername(), getPassword());
			UserController.userName = getUsername();
			UserController.password = getPassword();

			items.add(0, UserController.userName);
			items.add(1, UserController.password);
			items.add(2, "user");
			String logInAutorization = UserController.login(items);

			if (!(logInAutorization == null)) {
				if (logInAutorization.equals("ERROR-LOGIN") || logInAutorization.equals("The user does not exist")) {
					items.add(2, "restaurant");
					logInAutorization = UserController.login(items);
					UserController.getRestaurantDetails(getUsername(), getPassword());
					if (logInAutorization.equals("ERROR-LOGIN")
							|| logInAutorization.equals("The user does not exist")) {
						items.add(2, "customers");
						logInAutorization = UserController.login(items);
						UserController.getCustomerDetails(getUsername(), getPassword());

					}

				}
			}

			System.out.println(logInAutorization);
			switch (logInAutorization) {
			case "User":
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("Data did not imported!");
				lblMessage.setTextFill(Color.RED);
				break;
			case "You must fill all the fields":
				if (getUsername().isEmpty())
					txtUsername.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
				else
					txtUsername.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
				if (getPassword().isEmpty())
					txtPassword.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
				else
					txtPassword.setStyle(
							"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("You must fill all the fields");
				lblMessage.setTextFill(Color.RED);
				break;
			case "The user does not exist":
				System.out.println(" inside The user does not exist");
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("The user does not exist");
				lblMessage.setTextFill(Color.RED);
				break;

			case "The password is incorrect":

				txtUsername.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

				txtPassword.setStyle(
						"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");

				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("The password is incorrect");
				lblMessage.setTextFill(Color.RED);
				break;

			case "The user is already connected":
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("The user is already connected");
				lblMessage.setTextFill(Color.RED);
				break;

			case "BMManager": // openManagerForm();
				FXMLLoader BMManager_loader = new FXMLLoader(getClass().getResource("BMManagerForm.fxml"));
				Parent BMManager_Root;
				try {
					BMManager_Root = BMManager_loader.load();
					ScreenControllers.ManagerFormController = BMManager_loader.getController();
					Scene scene = new Scene(BMManager_Root);
					UserController.currentStage.setScene(scene);
					UserController.currentStage.setTitle("BMManager page");
					ScreenControllers.ManagerFormController.start();

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case "HR": // openCustomerForm();
				FXMLLoader HR_loader = new FXMLLoader(getClass().getResource("HRForm.fxml"));
				Parent HR_Root;
				try {
					HR_Root = HR_loader.load();
					ScreenControllers.hrFormController = HR_loader.getController();
					Scene scene = new Scene(HR_Root);
					UserController.currentStage.setTitle("HR page");
					UserController.currentStage.setScene(scene);
					ScreenControllers.hrFormController.start();

				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case "CEO": // openCustomerForm();
				FXMLLoader CEO_loader = new FXMLLoader(getClass().getResource("CEOForm.fxml"));
				Parent CEO_Root;
				try {
					CEO_Root = CEO_loader.load();
					ScreenControllers.ceoFormController = CEO_loader.getController();
					Scene scene = new Scene(CEO_Root);
					UserController.currentStage.setTitle("CEO page");
					UserController.currentStage.setScene(scene);
					ScreenControllers.ceoFormController.start();

				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case "Restaurant": // openEmployeeForm();

				UserController.getRestaurantDetails(getUsername(), getPassword());
				FXMLLoader Restaurant_loader = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
				Parent Restaurant_Root;
				try {
					Restaurant_Root = Restaurant_loader.load();
					ScreenControllers.restaurantFormController = Restaurant_loader.getController();
					Scene scene = new Scene(Restaurant_Root);
					UserController.currentStage.setTitle("Restaurant page");
					UserController.currentStage.setScene(scene);
					ScreenControllers.restaurantFormController.start();

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case "Customer": // openCustomerForm();
				FXMLLoader Customer_loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
				Parent Customer_Root;
				try {
					Customer_Root = Customer_loader.load();
					ScreenControllers.customerFormController = Customer_loader.getController();
					Scene scene = new Scene(Customer_Root);
					UserController.currentStage.setTitle("Customer page");
					UserController.currentStage.setScene(scene);
					ScreenControllers.customerFormController.start();

				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			default:
				BMClientUI.display("cant read message from server");
			}

		}
	}

	/**
	 * Method exit from the client system
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	public void getExitBtn(MouseEvent event) throws Exception {
		System.out.println("Come back soon:)");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("IpForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			ScreenControllers.ipFormController = loader.getController();
			Scene scene = new Scene(root);
			UserController.currentStage.setScene(scene);

			ScreenControllers.ipFormController.start(UserController.currentStage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
