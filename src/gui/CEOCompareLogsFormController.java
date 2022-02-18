package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import logic.Log;
import logic.Order;
import logic.Restaurant;

/**
 * This class defines the controller for comparing income Logs
 * 
 *
 */
public class CEOCompareLogsFormController implements Initializable {

	// Log 1

	// Combo box can be the same name initialize one time , the methods different

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

	///// Log 2

	@FXML
	private ComboBox<String> cmpRestaurants1;
	ArrayList<String> Restaurant1 = new ArrayList<String>();
	private ObservableList<String> Restaurantlist1;

	@FXML
	private ComboBox<String> cmpMonth1;
	ArrayList<String> Months1 = new ArrayList<String>();
	private ObservableList<String> Monthslist1;

	@FXML
	private TableView<Order> tblLogs1 = new TableView<Order>();;

	@FXML
	private TableColumn<Order, String> orderColumn1;

	@FXML
	private TableColumn<Order, String> customerColumn1;

	@FXML
	private TableColumn<Order, String> dateColumn1;

	@FXML
	private TableColumn<Order, String> priceColumn1;

	@FXML
	private Label lblBranchPrompt1;

	@FXML
	private TextField txtTotalPrice1;

	@FXML
	private Label lblRestaurantName1;

	@FXML
	private Label lblMonthName1;

	@FXML
	private Label lblMessage1;

	@FXML
	private Button btnSendPDF1;

	private ArrayList<Order> dataToCollect;
	private ArrayList<Order> order;
	private ArrayList<Log> logs;
	private String totalPrice;

	private String month, restaurantBranch, restaurantName;
	private ArrayList<Restaurant> res;
	private ArrayList<Order> dataToCollect1;
	private ArrayList<Order> order1;
	private ArrayList<Log> logs1;
	private String totalPrice1;
	private String month1, restaurantBranch1, restaurantName1;
	private ArrayList<Restaurant> res1;

	/**
	 * Initialize is the first to happen on this class it defines all the data to
	 * combo boxes and lables for both logs one and two
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.restaurantBranch = UserController.address;
		lblRestaurantName.setText(restaurantBranch);
		lblRestaurantName.setFont(new Font("Arial", 16));
		lblRestaurantName.setStyle("-fx-font-weight: bold");
		res = OrderController.getAllRestaurantsFullName(null);

		order = OrderController.getAllOrderDetails(null);
		logs = OrderController.getLogs(null);

		setComboBox();

		this.restaurantBranch1 = UserController.address;
		lblRestaurantName1.setText(restaurantBranch1);
		lblRestaurantName1.setFont(new Font("Arial", 16));
		lblRestaurantName1.setStyle("-fx-font-weight: bold");
		res1 = OrderController.getAllRestaurantsFullName(null);

		order1 = OrderController.getAllOrderDetails(null);
		logs1 = OrderController.getLogs(null);

		setComboBox1();

	}

	/**
	 * This function sets the comboBoxes for this window for first log
	 */
	private void setComboBox() {
		for (int i = 0; i < res.size(); i++) {
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
	 * This function sets the comboBoxes for this window for second log
	 */
	private void setComboBox1() {
		for (int i = 0; i < res1.size(); i++) {
			Restaurant1.add(res1.get(i).getRestaurantName());
		}

		Restaurantlist1 = FXCollections.observableArrayList(Restaurant1);
		cmpRestaurants1.setItems(Restaurantlist1);

		Months1.add("01");
		Months1.add("02");
		Months1.add("03");
		Months1.add("04");
		Months1.add("05");
		Months1.add("06");
		Months1.add("07");
		Months1.add("08");
		Months1.add("09");
		Months1.add("10");
		Months1.add("11");
		Months1.add("12");
		Monthslist1 = FXCollections.observableArrayList(Months1);
		cmpMonth1.setItems(Monthslist1);

	}

	/**
	 * This method defines what happens when a month is chosen out of combo-box It
	 * sets all the data for the income logs out of the right functions from the
	 * controller (for first log)
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedMonthMethodLog1(ActionEvent event) {

		totalPrice = "0";
		dataToCollect = new ArrayList<Order>();

		this.month = cmpMonth.getValue();

		for (int i = 0; i < order.size(); i++) {
			String monthNumber = order.get(i).getDateOfOrder();
			String[] orderDate = monthNumber.split("-");
			String month = orderDate[1];
			if ((order.get(i).getRestaurantName().equals(restaurantName)) && month.equals(this.month)) {
				dataToCollect.add(order.get(i));
			}

		}

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

	/**
	 * This method defines what happens when you choose a restaurant in combo-box
	 * (setting the chosen name to a label) for first log
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedRestaurantMethodLog1(ActionEvent event) {
		dataToCollect = new ArrayList<Order>();
		totalPrice = "0";
		lblRestaurantName.setText(cmpRestaurants.getValue());
		this.restaurantName = cmpRestaurants.getValue();
	}

	/**
	 * This method defines what happens when you choose a restaurant in combo-box
	 * (setting the chosen name to a label) for second log
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedRestaurantMethodLog2(ActionEvent event) {
		dataToCollect1 = new ArrayList<Order>();
		totalPrice1 = "0";
		lblRestaurantName1.setText(cmpRestaurants1.getValue());
		this.restaurantName1 = cmpRestaurants1.getValue();
	}

	/**
	 * This method defines what happens when a month is chosen out of combo-box It
	 * sets all the data for the income logs out of the right functions from the
	 * controller (for second log)
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedMonthMethodLog2(ActionEvent event) {
		dataToCollect1 = new ArrayList<Order>();
		totalPrice1 = "0";

		this.month1 = cmpMonth1.getValue();

		for (int i = 0; i < order.size(); i++) {
			String monthNumber1 = order.get(i).getDateOfOrder();
			String[] orderDate = monthNumber1.split("-");
			String month1 = orderDate[1];
			if ((order1.get(i).getRestaurantName().equals(restaurantName1)) && month1.equals(this.month1)) {
				dataToCollect1.add(order1.get(i));
			}

		}

		ObservableList<Order> data_order1 = FXCollections.observableArrayList(dataToCollect1);
		orderColumn1.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		customerColumn1.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		dateColumn1.setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
		priceColumn1.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));
		tblLogs1.setItems(data_order1);

		for (int i = 0; i < logs1.size(); i++) {
			if (month1.equals(logs.get(i).getMonthNum()) && restaurantName1.equals(logs1.get(i).getRestaurantName()))
				totalPrice1 = logs1.get(i).getIncomes();
		}
		txtTotalPrice1.setText(totalPrice1);

	}

	/**
	 * This method defines the button to get back to view logs screen for CEO
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("CEO page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
