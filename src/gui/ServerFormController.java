
package gui;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Customer;
import logic.Order;
import logic.Restaurant;
import logic.UserManagment;
import server.BMServerUI;
import server.MainServer;

public class ServerFormController implements Initializable {

	ArrayList<UserManagment> allUsersList = new ArrayList<UserManagment>();
	ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	ArrayList<Customer> customerList = new ArrayList<Customer>();

	public String ServerIP;

	private String username, password;

	@FXML
	private TextField txtIP;
	@FXML
	private TextField txtHost;
	@FXML
	private TextField txtDBurl;
	@FXML
	private TextField txtDBUser;
	@FXML
	private PasswordField txtDBPassword;
	@FXML
	private Button ClientListBtn;
	@FXML
	private Button ConnectBtn;
	@FXML
	private Button importBtn;
	@FXML
	private Button ExitBtn;
	@FXML
	private Label lblMessage;

	// First of all show the server form window
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("ServerForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMe Server");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// To get server IP Address
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("google.com", 80));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ServerIP = socket.getLocalAddress().toString();
		ServerIP = ServerIP.substring(1);
		txtIP.setText(ServerIP);
		txtDBurl.setText("jdbc:mysql://localhost:3306/test");
		txtHost.setText("5555");
		txtDBUser.setText("root");
		txtDBPassword.setText("");

		importBtn.setDisable(true);

	}

	private String getTxtHost() {
		return txtHost.getText();
	}

	public String getTxtDBurl() {
		return txtDBurl.getText() + "?serverTimezone=IST";

	}

	public String getTxtDBUser() {
		return txtDBUser.getText();
	}

	public String getTxtDBPassword() {
		return txtDBPassword.getText();
	}

	@FXML
	void getClientListBtn(ActionEvent event) throws IOException {
//		((Node) event.getSource()).getScene().getWindow().hide(); // hide main window
		FXMLLoader loader = new FXMLLoader();
		AnchorPane root = (AnchorPane) loader.load(getClass().getResource("ClientsListForm.fxml").openStream());
		Stage primaryStage = new Stage();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("BiteMe Clients");
		primaryStage.show();
	}

	@FXML
	void getConnectBtn(ActionEvent event) throws SocketException {
		Boolean connectionFlag;
		String host, url;
		host = getTxtHost();
		url = getTxtDBurl();
		username = getTxtDBUser();
		password = getTxtDBPassword();
		if (host.trim().isEmpty() || url.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must fill the requaierd fields");
		} else {

			// ((Node) event.getSource()).getScene().getWindow().hide(); // hide main window

			BMServerUI.runServer(host);
			connectionFlag = DB.DBConnector.connectToDB(url, username, password);

			if (connectionFlag) {

				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Server Connected");
				this.lblMessage.setTextAlignment(TextAlignment.CENTER);
				ConnectBtn.setDisable(true);

				importBtn.setDisable(false);

				// if click import once set disable true
				boolean usersCheckExistFlag = DB.UserDBController.usersCheckExist();

				if (usersCheckExistFlag)

					importBtn.setDisable(true);
				else {
					lblMessage.setTextFill(Color.BLACK);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("Import data please");
					importBtn.setDisable(false);
				}

			}

		}
	}

	@FXML
	void getImportBtn(ActionEvent event) throws SocketException {
		Boolean connectionOutsideFlag;
		connectionOutsideFlag = DB.DBConnector
				.connectToDB("jdbc:mysql://localhost:3306/users_managment?serverTimezone=IST", username, password);
		if (connectionOutsideFlag) {
			importBtn.setDisable(true);
			lblMessage.setTextFill(Color.GREEN);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("Import data succeed");

			allUsersList = DB.UserDBController.getDataFromOutside();

			for (int i = 0; i < allUsersList.size(); i++) {
				String authorization = allUsersList.get(i).getAuthorization();

				if (authorization.equals("User")) {
					String userName = allUsersList.get(i).getUserName();
					if (userName.charAt(0) == 'u') {
						Customer customer = new Customer(allUsersList.get(i).getiD(), allUsersList.get(i).getUserName(),
								allUsersList.get(i).getPassword(), allUsersList.get(i).getFirstName(),
								allUsersList.get(i).getLastName(), allUsersList.get(i).getAddress(),
								allUsersList.get(i).getEmail(), allUsersList.get(i).getPhone(),
								allUsersList.get(i).getAuthorization());

						boolean addCustomersFlag = DB.UserDBController.addCustomers(customer);

					} else {

						Restaurant restaurant = new Restaurant(allUsersList.get(i).getiD(),
								allUsersList.get(i).getUserName(), allUsersList.get(i).getPassword(),
								allUsersList.get(i).getFirstName(), allUsersList.get(i).getAddress(),
								allUsersList.get(i).getEmail(), allUsersList.get(i).getPhone(),
								allUsersList.get(i).getAuthorization());

						boolean addRestaurantsFlag = DB.UserDBController.addRestaurants(restaurant);

					}
				} else {
					UserManagment user = new UserManagment(allUsersList.get(i).getiD(),
							allUsersList.get(i).getUserName(), allUsersList.get(i).getPassword(),
							allUsersList.get(i).getFirstName(), allUsersList.get(i).getLastName(),
							allUsersList.get(i).getAddress(), allUsersList.get(i).getEmail(),
							allUsersList.get(i).getPhone(), allUsersList.get(i).getAuthorization(),
							allUsersList.get(i).getStatus());

					boolean addUsersFlag = DB.UserDBController.addUsers(user);
				}

			}

		}

	}

	@FXML
	void getExitBtn(ActionEvent event) {
		System.out.println("Exit Server Window");
		System.exit(0);
	}

}
