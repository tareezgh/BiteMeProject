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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Order;

public class RestaurantCreateMenuFormController implements Initializable {

	@FXML
	private Label lblMessage;

	@FXML
	private TextField txtMealName;
	@FXML
	private TextField txtPriceBig;
	@FXML
	private TextField txtPriceMedium;
	@FXML
	private TextField txtPriceSmall;

	@FXML
	public static ImageView imgBack;

	String selectedCategory;
	String MealName;
	String PriceBig;
	String PriceMedium;
	String PriceSmall;

	ArrayList<String> Items = new ArrayList<String>(); // restaurant name, meal name, category , size , price by size
	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		Items.add(0, UserController.firstName);

	}

	/**
	 * Method to set the data in a combo box
	 */
	private void setComboBox() {
		Category.add("Starter");
		Category.add("Main Course");
		Category.add("Salad");
		Category.add("Drinks");
		Category.add("Dessert");
		Categorylist = FXCollections.observableArrayList(Category);
		cmpCategory.setItems(Categorylist);
	}

	/**
	 * Method to get the selected category
	 */
	@FXML
	public void getSelectedCategory() {
		txtMealName.setText("");
		txtPriceBig.setText("");
		txtPriceMedium.setText("");
		txtPriceSmall.setText("");
		txtMealName.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		txtPriceBig.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		txtPriceMedium.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		txtPriceSmall.setStyle(
				"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		selectedCategory = cmpCategory.getValue();
		Items.add(1, selectedCategory);
	}

	/**
	 * Method that add order with its prices to the menu
	 * Checks if the user filled all the fields
	 * @param event
	 */
	@FXML
	void clickedAddOrderMouse(MouseEvent event) {
		MealName = txtMealName.getText();
		PriceBig = txtPriceBig.getText();
		PriceMedium = txtPriceMedium.getText();
		PriceSmall = txtPriceSmall.getText();
		if (MealName.isEmpty() || PriceBig.isEmpty() || PriceMedium.isEmpty() || PriceSmall.isEmpty()) {
			checkValidFileds();
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must fill all the fields");
		} else {
			Items.add(2, MealName);

			Items.add(3, "Big");
			Items.add(4, PriceBig);
			boolean addBigMealToMenu = OrderController.addMealToMenu(Items);

			Items.add(3, "Medium");
			Items.add(4, PriceMedium);
			boolean addMediumMealToMenu = OrderController.addMealToMenu(Items);

			Items.add(3, "Small");
			Items.add(4, PriceSmall);
			boolean addSmallMealToMenu = OrderController.addMealToMenu(Items);

			if (addBigMealToMenu && addMediumMealToMenu && addSmallMealToMenu) {
				txtMealName.setText(" ");
				txtPriceBig.setText(" ");
				txtPriceMedium.setText(" ");
				txtPriceSmall.setText(" ");
				cmpCategory.setValue(" ");
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Adding " + MealName + " to the menu");
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Error Adding " + MealName + " to the menu");
			}
		}

	}

	/**
	 * Method that checks if the user filled all the required fields
	 */
	void checkValidFileds() {

		if (txtMealName.getText().isEmpty())
			txtMealName.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtMealName.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		if (txtPriceBig.getText().isEmpty())
			txtPriceBig.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtPriceBig.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");

		if (txtPriceMedium.getText().isEmpty())
			txtPriceMedium.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtPriceMedium.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
		if (txtPriceSmall.getText().isEmpty())
			txtPriceSmall.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: red");
		else
			txtPriceSmall.setStyle(
					"-fx-background-radius: 5em; -fx-border-radius: 5em; -fx-background-color: #fff; -fx-border-color: black");
	}

	/**
	 * Method that loads the next window
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getDoneBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Restaurant page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
	}

}
