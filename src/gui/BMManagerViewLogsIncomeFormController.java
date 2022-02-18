package gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.OrderController;
import client.UserController;
import common.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import logic.Log;
import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.Restaurant;

/**
 * This class defines the controller for income Logs
 *
 */
public class BMManagerViewLogsIncomeFormController implements Initializable {

	private ArrayList<Order> dataToCollect;
	private ArrayList<Order> order;
	private ArrayList<Log> logs;
	private String totalPrice;
	private String month, restaurantBranch, restaurantName, fileName;
	private ArrayList<Restaurant> res;
	
	@FXML
	private Button BtnBack;
	@FXML
	private ComboBox<String> cmpRestaurants;
	ArrayList<String> Restaurant = new ArrayList<String>();
	private ObservableList<String> Restaurantlist;
	@FXML
	private ComboBox<String> cmpMonth;
	ArrayList<String> Months = new ArrayList<String>();
	private ObservableList<String> Monthslist;
	@FXML
	private TableView<Order> tblLogs = new TableView<Order>();;
	@FXML
	private TableColumn<Order, String> orderColumn;
	@FXML
	private TableColumn<Order, String> customerColumn;
	@FXML
	private TableColumn<Order, String> dateColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;
	@FXML
	private Label lblBranchPrompt;
	@FXML
	private TextField txtTotalPrice;
	@FXML
	private Label lblRestaurantName;
	@FXML
	private Label lblMonthName;
	@FXML
	private Label lblMessage;
	@FXML
	private Button btnSendPDF;
	
	

	/**
	 * This function defines the button which sends the file to the database from
	 * BM-manager page view It takes the existed file out of "client" folder on the
	 * PC (Desktop)
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getSendAsPDFBtn(ActionEvent event) throws Exception {
		LocalDate current_date = LocalDate.now();
		int current_Year = current_date.getYear();
		fileName = month + "-" + String.valueOf(current_Year) + "-Income-" + restaurantBranch + ".pdf";
		String serverString = "C:\\client\\";   // /Users/tareezghandour/Desktop/client/
		MyFile msgFile = new MyFile(fileName);
		String LocalfilePath = serverString + fileName; // my Actual file

		try {

			File newFile = new File(LocalfilePath);

			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			msgFile.initArray(mybytearray.length);
			msgFile.setSize(mybytearray.length);

			bis.read(msgFile.getMybytearray(), 0, mybytearray.length);

			Message msg = new Message(MessageType.sendFile, msgFile);
			BMClientUI.accept(msg);
			lblMessage.setText("System exported the report for this month");

		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
			lblMessage.setText("File does not exist");
		}

	}

	/**
	 * This method defines what happens when a month is chosen out of combo-box It
	 * sets all the data for the income logs out of the right functions from the
	 * controller
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedMonthMethod(ActionEvent event) {
		totalPrice = "0";
		dataToCollect = new ArrayList<Order>();
		lblMonthName.setText(cmpMonth.getValue());
		this.month = cmpMonth.getValue();

		displayTable();
	}

	/**
	 * This method defines what happens when you choose a restaurant in combo-box
	 * (setting the chosen name to a label)
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedRestaurantMethod(ActionEvent event) {
		dataToCollect = new ArrayList<Order>();
		totalPrice = "0";
		lblRestaurantName.setText(cmpRestaurants.getValue());
		this.restaurantName = cmpRestaurants.getValue();
		displayTable();
	}

	/**
	 * This function shows all data to the user when he choses the relevant month
	 * and restaurant. it uses all the relevant data to show order logs + using logs
	 * which is collected every month on the data base
	 */
	private void displayTable() {
		if (month != null && restaurantName != null) {

			for (int i = 0; i < order.size(); i++) {
				String monthNumber = order.get(i).getDateOfOrder();
				String[] orderDate = monthNumber.split("-");

				String month = orderDate[1];

				if ((order.get(i).getRestaurantName().equals(restaurantName)) && month.equals(this.month)) {
					dataToCollect.add(order.get(i));
				}

			}
			// putting all the data inside the table
			ObservableList<Order> data_order = FXCollections.observableArrayList(dataToCollect);
			orderColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
			customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
			priceColumn.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));
			tblLogs.setItems(data_order);

			for (int i = 0; i < logs.size(); i++) {
				if (month.equals(logs.get(i).getMonthNum()) && restaurantName.equals(logs.get(i).getRestaurantName()))
					totalPrice = logs.get(i).getIncomes();
			}
			txtTotalPrice.setText(totalPrice);
		}
	}

	/**
	 * Initialize is the first to happen on this class it defines all the data to
	 * combo boxes and lables
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.restaurantBranch = UserController.address;
		lblRestaurantName.setText(restaurantBranch);
		lblRestaurantName.setFont(new Font("Arial", 16));
		lblRestaurantName.setStyle("-fx-font-weight: bold");
		res = OrderController.getAllRestaurantsFullName(null);
		ObservableList<Restaurant> data_res = FXCollections.observableArrayList();
		order = OrderController.getAllOrderDetails(null);
		logs = OrderController.getLogs(null);
		// start setting combo boxes
		setComboBox();

	}

	/**
	 * This function sets the comboBoxes for this window
	 */
	private void setComboBox() {

		for (int i = 0; i < res.size(); i++) {

			if (res.get(i).getRestaurantBranch().equals(restaurantBranch))
				Restaurant.add(res.get(i).getRestaurantName());
		}

		Restaurantlist = FXCollections.observableArrayList(Restaurant);
		cmpRestaurants.setItems(Restaurantlist);

		Months.add("01");
		Months.add("02");
		Months.add("03");
		Months.add("04");
		Months.add("05");
		Months.add("06");
		Months.add("07");
		Months.add("08");
		Months.add("09");
		Months.add("10");
		Months.add("11");
		Months.add("12");
		Monthslist = FXCollections.observableArrayList(Months);
		cmpMonth.setItems(Monthslist);

	}

	/**
	 * This method defines the button to get back to view logs screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerViewLogsForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("View Logs page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}