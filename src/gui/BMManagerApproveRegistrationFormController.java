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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Employer;
import logic.Order;

public class BMManagerApproveRegistrationFormController implements Initializable {
	ArrayList<String> getItems = new ArrayList<String>(); // UserID,OrderNum , MealNum
	private ToggleGroup group = new ToggleGroup();

	private String selectedEmployeeCode;

	@FXML
	private Label lblMessage;
	@FXML
	private RadioButton ApprovedBtn;
	@FXML
	private RadioButton DeclineBtn;
	@FXML
	private TableView<Employer> EmployeeTbl = new TableView<Employer>();
	@FXML
	private TableColumn<Employer, String> employerCodeColumn;
	@FXML
	private TableColumn<Employer, String> employerNameColumn;
	@FXML
	private TableColumn<Employer, String> statusColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayTable();
	}

	@FXML
	public void getSelectedEmployee(MouseEvent event) {
		selectedEmployeeCode = EmployeeTbl.getSelectionModel().getSelectedItem().getEmployerCode();
		getItems.add(0, selectedEmployeeCode);
	}

	/**
	 * Method update the status to Approve/Decline
	 * 
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getUpdateBtn(MouseEvent event) throws Exception {
		boolean next = true;
		if (ApprovedBtn.isSelected()) {
			getItems.add(1, "Approve");
		} else if (DeclineBtn.isSelected()) {
			getItems.add(1, "Decline");
		} else {
			next = false;
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must select status");
		}

		if (next) {
			if (UserController.changeEmployerStatus(getItems)) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status changed");
				displayTable();
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Error changing status");
			}
		}

	}

	/**
	 * Method take us back to the previous page
	 * @param event - Click On Mouse Event
	 * @throws Exception
	 */
	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("BM Manager page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method insert the data into the table
	 */
	private void displayTable() {
		ArrayList<Employer> employeeList = UserController.getEmployeeDetails("Requested");
		ObservableList<Employer> data = FXCollections.observableArrayList(employeeList);

		employerCodeColumn.setCellValueFactory(new PropertyValueFactory<>("employerCode"));
		employerNameColumn.setCellValueFactory(new PropertyValueFactory<>("employerName"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("employerStatus"));

		EmployeeTbl.setItems(data);
	}

}
