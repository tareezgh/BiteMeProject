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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import logic.Log;
import logic.Order;
import logic.Restaurant;

/**
 * This class defines the Controller for view order logs
 *
 */
public class BMManagerViewLogsOrderFormController implements Initializable {

	@FXML
	private ComboBox<String> cmpRestaurants;
	ArrayList<String> Restaurant = new ArrayList<String>();
	private ObservableList<String> Restaurantlist;

	@FXML
	private ComboBox<String> cmpMonth;
	ArrayList<String> Months = new ArrayList<String>();
	private ObservableList<String> Monthslist;

	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	@FXML
	private TableView<Order> tblLogs = new TableView<Order>();;

	@FXML
	private TableColumn<Order, String> orderColumn;

	@FXML
	private TableColumn<Order, String> customerColumn;

	@FXML
	private TableColumn<Order, String> dateColumn;
	@FXML
	private TableColumn<Order, String> mealColumn;

	@FXML
	private TextField txtTotalMealCount;

	@FXML
	private Label lblRestaurantName;

	@FXML
	private Label lblCategoryName;

	@FXML
	private TextField txtTotalMealCategory;

	@FXML
	private Label lblMessage; // Relevant Message to on screen

	private ArrayList<Order> dataToCollect;
	private ArrayList<Order> order;
	private ArrayList<Log> logs;
	private String category;
	private char[] monthNum = new char[2];
	private String mealsNumber, month, restaurantBranch, restaurantName;
	private ArrayList<Restaurant> res;
	private int categoryCount;

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
		lblCategoryName.setFont(new Font("Arial", 16));
		lblCategoryName.setStyle("-fx-font-weight: bold");
		res = OrderController.getAllRestaurantsFullName(null);
		order = OrderController.getAllOrderDetails(null);
		logs = OrderController.getLogs(null);
		setComboBox();

	}

	/**
	 * Setting all comboBoxes for this window
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

		Category.add("Starter");
		Category.add("Salad");
		Category.add("Main Course");
		Category.add("Drinks");
		Category.add("Dessert");
		Categorylist = FXCollections.observableArrayList(Category);
		cmpCategory.setItems(Categorylist);

	}

	/**
	 * This method defines what happens when month is chosen Saves the value of the
	 * month
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedMonthMethod(ActionEvent event) {
		categoryCount = 0;
		mealsNumber = "0";
		dataToCollect = new ArrayList<Order>();
		this.month = cmpMonth.getValue();

		displayTable();

	}

	/**
	 * This function shows all data to the user when he choses the relevant month it
	 * uses all the relevant data to show order logs + using logs which is collected
	 * every month on the data base
	 */
	public void displayTable() {

		if (month != null && restaurantName != null) {
			for (int i = 0; i < order.size(); i++) {
				String monthNumber = order.get(i).getDateOfOrder();
				String[] orderDate = monthNumber.split("-");

				String month = orderDate[1];
				if ((order.get(i).getRestaurantName().equals(restaurantName)) && month.equals(this.month)
						&& category.equals(order.get(i).getCategory())) {
					dataToCollect.add(order.get(i));
					categoryCount++;
				}
			}
			System.out.println("dataToCollect: " + dataToCollect);

			ObservableList<Order> data_order = FXCollections.observableArrayList(dataToCollect);
			orderColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
			customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
			mealColumn.setCellValueFactory(new PropertyValueFactory<>("meal"));
			tblLogs.setItems(data_order);

			for (int i = 0; i < logs.size(); i++) {
				if (month.equals(logs.get(i).getMonthNum()) && restaurantName.equals(logs.get(i).getRestaurantName()))
					mealsNumber = logs.get(i).getNumberOfOrders();
			}
			txtTotalMealCount.setText(mealsNumber);
			txtTotalMealCategory.setText(String.valueOf(categoryCount));
		}

	}

	/**
	 * This method defines what happens when the user select combo-box (saves labels
	 * and displays data)
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedCategoryMethod(ActionEvent event) {
		categoryCount = 0;
		mealsNumber = "0";
		dataToCollect = new ArrayList<Order>();
		lblCategoryName.setText(cmpCategory.getValue());
		this.category = cmpCategory.getValue();
		displayTable();
	}

	/**
	 * This method defines what happens when the user select combo-box (saves labels
	 * and displays data)
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedRestaurantMethod(ActionEvent event) {
		categoryCount = 0;
		mealsNumber = "0";
		dataToCollect = new ArrayList<Order>();
		lblRestaurantName.setText(cmpRestaurants.getValue());
		this.restaurantName = cmpRestaurants.getValue();
		displayTable();
	}

	/**
	 * This method defines the button to get back to view logs screen for BM-manager
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
			UserController.currentStage.setTitle("View logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
