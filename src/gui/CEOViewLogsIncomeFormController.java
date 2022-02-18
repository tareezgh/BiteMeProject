package gui;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import logic.FileData;
import logic.Log;
import logic.Order;
import logic.Restaurant;

/**
 * This class defines the controller for income Logs for CEO
 * 
 *
 */
public class CEOViewLogsIncomeFormController implements Initializable {

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

	private ArrayList<Order> dataToCollect;
	private ArrayList<Order> order;
	private ArrayList<Log> logs;
	private ArrayList<FileData> files;
	private String totalPrice;
	private String month, restaurantBranch, restaurantName, fileName;
	private ArrayList<Restaurant> res;

	/**
	 * This function defines the button which downloads the file to the PC from DB
	 * It saves the file to server folder and uses function to downlad BLOB from DB.
	 * PC (Desktop)
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void getDownloadAsPDFBtn(ActionEvent event) throws Exception {
		FileData fileToSave = null;
		LocalDate current_date = LocalDate.now();
		int current_Year = current_date.getYear();
		fileName = month + "-" + String.valueOf(current_Year) + "-Income-" + restaurantBranch + ".pdf";
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getFileName().equals(fileName))
				fileToSave = files.get(i);
		}
		if (fileToSave == null) {
			lblMessage.setText("This file is not on the system");
			return;
		}

		MyFile saveFile = new MyFile(fileToSave.getFileName());
		saveFile.setSize(fileToSave.getSize());
		saveFile.initArray(fileToSave.getByteArray().length);
		saveFile.setSize(fileToSave.getByteArray().length);
		saveFile.setMybytearray(fileToSave.getByteArray());
		int fileSize = fileToSave.getSize();
		System.out.println("Message received: " + fileToSave.getFileName());
		System.out.println("length " + fileSize);

		try {
			FileOutputStream fos;
			String serverLocation = "C:\\server\\";
			fos = new FileOutputStream(serverLocation + saveFile.getFileName());
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(saveFile.getMybytearray(), 0, fileSize);
			bos.flush();
			fos.flush();
			lblMessage.setText("File was downloaded to you PC");
		} catch (IOException e) {
			System.out.println("Unexpected error while writing");
			lblMessage.setText("Could not download the file");
		}

	}

	/**
	 * This function shows all data to the user when he choses the relevant month
	 * and restaurant. it uses all the relevant data to show order logs + using logs
	 * which is collected every month on the data base
	 */
	public void displayTable() {
		if (month != null && restaurantName != null) {
			for (int i = 0; i < order.size(); i++) {
				String monthNumber = order.get(i).getDateOfOrder();
				String[] orderDate = monthNumber.split("-");
				String month = orderDate[1];

				if ((order.get(i).getRestaurantName().equals(restaurantName)) && month.equals(this.month)) {
					dataToCollect.add(order.get(i));
					this.restaurantBranch = order.get(i).getRestaurantBranch();
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
	 * Initialize is the first to happen on this class it defines all the data to
	 * combo boxes and lables
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lblRestaurantName.setFont(new Font("Arial", 16));
		lblRestaurantName.setStyle("-fx-font-weight: bold");
		res = OrderController.getAllRestaurantsFullName(null);
		ObservableList<Restaurant> data_res = FXCollections.observableArrayList();
		order = OrderController.getAllOrderDetails(null);
		logs = OrderController.getLogs(null);
		files = OrderController.getFiles(null);
		setComboBox();

	}

	/**
	 * This function sets the comboBoxes for this window
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
	 * This method defines the button to get back to view logs screen
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
