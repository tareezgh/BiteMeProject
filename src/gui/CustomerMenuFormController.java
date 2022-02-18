package gui;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import logic.Order;

public class CustomerMenuFormController implements Initializable {
	ArrayList<String> meals = new ArrayList<String>();

	ArrayList<String> getMealsInRestaurantItems = new ArrayList<String>(); // Restaurant name, Category
	ArrayList<String> getMealPriceItems = new ArrayList<String>(); // Meal name, Size

	private Order order;
	private String mealPrice;

	@FXML
	private Label txtPrice;
	@FXML
	private Label txtTotalPrice;
	@FXML
	private Text title;
	@FXML
	private Label lblMessage;
	@FXML
	private ListView<String> MealList;

	@FXML
	private TextField txtRestricions;
	@FXML
	private ImageView imgAdd;

	// Category
	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	// Size
	@FXML
	private ComboBox<String> cmpSize1;
	ArrayList<String> Size = new ArrayList<String>();
	private ObservableList<String> Sizelist;

	// LvlOfCook
	@FXML
	private ComboBox<String> cmpLvlOfCook;
	ArrayList<String> LvlOfCook = new ArrayList<String>();
	private ObservableList<String> LvlOfCooklist;

	/**
	 * Method that gets the meals prices calculates how many orders the user have
	 * already
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.title.setText("    " + OrderController.selectedRestaurantName + " Menu");

		getMealPriceItems.add(0, OrderController.selectedRestaurantName);
		setComboBox();

		int intOrderNumber[] = { 0 };
		int max = 0;
		mealPrice = null;

		// ** To know how many orders the user have already
		ArrayList<Order> ordersListByID = OrderController.getOrderDetailsByID(UserController.userID);
		if (ordersListByID.isEmpty()) {
			OrderController.orderNumber = 1; // reset order numbers for customer who haven't order yet
		} else { // find the last order number

			intOrderNumber = new int[ordersListByID.size()];
			for (int i = 0; i < ordersListByID.size(); i++) {
				intOrderNumber[i] = (Integer.valueOf(ordersListByID.get(i).getOrderNumber()));

			}
			System.out.println("OrderController.orderNumber " + OrderController.orderNumber); // ******
			max = intOrderNumber[0];
			for (int i = 0; i < ordersListByID.size(); i++) {
				if (intOrderNumber[i] > max) {
					max = intOrderNumber[i];
				}
			}
			OrderController.orderNumber = max + 1;
		}

		cmpSize1.setDisable(true);
		cmpLvlOfCook.setDisable(true);
		txtRestricions.setDisable(true);

		txtPrice.setFont(new Font("Arial", 20));
		txtPrice.setStyle("-fx-font-weight: bold");

		txtTotalPrice.setFont(new Font("Arial", 20));
		txtTotalPrice.setStyle("-fx-font-weight: bold");
		txtTotalPrice.setText(String.valueOf(OrderController.totalPrice));
	}

	/**
	 * Method to select category and then load the meals according to the category
	 * that got selected
	 */
	@FXML
	public void getSelectedCategory() {
		cmpCategory.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		this.lblMessage.setText(" ");
		this.txtPrice.setText(" ");
		this.txtRestricions.setText(" ");
		this.cmpSize1.valueProperty().set("");
		this.cmpLvlOfCook.valueProperty().set("");

		OrderController.selectedCategory = cmpCategory.getValue();

		getMealsInRestaurantItems.add(0, OrderController.selectedRestaurantName);
		getMealsInRestaurantItems.add(1, OrderController.selectedCategory);

		// Set list of meals
		meals = OrderController.getMealsInRestaurant(getMealsInRestaurantItems);
		ObservableList<String> ObListMeals = FXCollections.observableArrayList(meals);
		MealList.setItems(ObListMeals);
		if (OrderController.selectedCategory != "Main Course") {
			cmpLvlOfCook.setDisable(true);
		} else
			cmpLvlOfCook.setDisable(false);
	}

	/**
	 * Method to select a meal and setting the size "big" as a default
	 * 
	 * @param event
	 */
	@FXML
	public void getSelectedMeal(MouseEvent event) {
		MealList.setStyle("-fx-background-color: #fff; -fx-border-color: black");
		OrderController.selectedMeal = MealList.getSelectionModel().getSelectedItem();
		if (OrderController.selectedMeal != null) {
			cmpSize1.setDisable(false);
			txtRestricions.setDisable(false);
		}

		cmpSize1.setValue("Big");
		OrderController.selectedSize = "Big";
		setPrice();
	}

	/**
	 * Method to get the size that the customer select
	 */
	@FXML
	public void getSelectedSize() {
		OrderController.selectedSize = cmpSize1.getValue();
		setPrice();
	}

	/**
	 * Method to get the prices of the meals from the DB
	 */
	public void setPrice() {
		getMealPriceItems.add(1, OrderController.selectedMeal);
		getMealPriceItems.add(2, OrderController.selectedSize);
		mealPrice = OrderController.getMealsInRestaurantPrice(getMealPriceItems); // get meal price from DB
		this.txtPrice.setText(mealPrice); // show meal price
	}

	@FXML
	public void getSelectedLvlOfCook() {
		OrderController.selectedLvlOfCook = cmpLvlOfCook.getValue();
	}

	/**
	 * Method that adds the meal price to total according to the size Sets the
	 * status of the meal to "Hold" until restaurant confirmation
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void clickedAddOrderMouse(MouseEvent event) {
		String customerID, meal, category, size, lvlOfCook, restrictions, status;
		String Key = OrderController.selectedMeal + "_" + OrderController.selectedSize;

		customerID = UserController.userID;
		category = OrderController.selectedCategory;
		size = OrderController.selectedSize;
		meal = OrderController.selectedMeal + "_" + size;
		lvlOfCook = OrderController.selectedLvlOfCook;
		restrictions = txtRestricions.getText();
		status = "Hold"; // until restaurant confirmation
		if (mealPrice != null) {
			OrderController.addFlag = true;
			OrderController.mealNum++;
			OrderController.totalPrice += Integer.valueOf(mealPrice); // add meal price to total
			this.txtTotalPrice.setText(String.valueOf(OrderController.totalPrice)); // show order total price

			order = new Order(String.valueOf(OrderController.orderNumber), customerID, meal, category, size, lvlOfCook,
					restrictions, status, " ");
			order.setQuantity("1");
			order.setMealNum(OrderController.mealNum);
			order.setMealPrice(mealPrice);
			order.setRestaurantName(OrderController.selectedRestaurantName);
			order.setRestaurantBranch(OrderController.selectedRestaurantBranch);

			OrderController.ordersList.add(order); // save meals in arrayList

			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText(Key + " Added succesfuly");
			lblMessage.setTextFill(Color.GREEN);

			// reset fields
			this.txtRestricions.setText(" ");

			this.cmpLvlOfCook.valueProperty().set("");

		} else {
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("Select meal please");
			lblMessage.setTextFill(Color.RED);
		}

	}

	/**
	 * Method that checks that the customer filled all the required fields Loads the
	 * next window
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getNextBtn(MouseEvent event) {
		if ((OrderController.selectedCategory == null || OrderController.selectedMeal == null)
				&& !OrderController.addFlag) {
			cmpCategory.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
			MealList.setStyle("-fx-background-color: #fff; -fx-border-color: red");
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must fill all the fields");
			lblMessage.setTextFill(Color.RED);

		} else if (OrderController.addFlag == false) { // at least one meal
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must add meal first");
			lblMessage.setTextFill(Color.RED);
		}

		else {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDeliveryForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Delivery details");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method that loads the "Customer View Current Order" window
	 * 
	 * @param event - Click On Mouse Event
	 */
	@FXML
	void getViewOrderBtn(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerViewCurrentOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Choose restaurant");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method take us back to the previous page
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	void getBackBtn(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerChooseRestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Choose restaurant");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that sets the data in the combo box
	 */
	private void setComboBox() {
		// Add category comboBox
		Category.add("Starter");
		Category.add("Main Course");
		Category.add("Salad");
		Category.add("Drinks");
		Category.add("Dessert");
		Categorylist = FXCollections.observableArrayList(Category);
		cmpCategory.setItems(Categorylist);

		// Add Size comboBox
		Size.add("Big");
		Size.add("Medium");
		Size.add("Small");
		Sizelist = FXCollections.observableArrayList(Size);
		cmpSize1.setItems(Sizelist);

		// Add Level of cook comboBox
		LvlOfCook.add("Well-Done");
		LvlOfCook.add("Medium-Well");
		LvlOfCook.add("Medium");
		LvlOfCooklist = FXCollections.observableArrayList(LvlOfCook);
		cmpLvlOfCook.setItems(LvlOfCooklist);

	}

}
