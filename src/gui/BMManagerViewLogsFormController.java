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
import javafx.stage.Stage;
import logic.Order;

/**
 * This class is a controller class for Main form of view logs
 * 
 */
public class BMManagerViewLogsFormController implements Initializable {
	@FXML
	private Button btnViewIncomeLogs;
	@FXML
	private Button btnViewOrderLogs;
	@FXML
	private Button btnViewPerformanceLogs;
	@FXML
	private Button btnExport;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * This function defines the button to the next the next screen for viewing
	 * income loges for BMmanager
	 * 
	 * @param event
	 * @throws Exception throws error in loading java-fx
	 */
	@FXML
	public void getViewIncomeLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerViewLogsIncomeForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Income logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defines the button to the next screen for viewing order loges
	 * for BMmanager
	 * 
	 * @param event
	 * @throws Exception throws error in loading java-fx
	 */
	@FXML
	public void getViewOrderLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerViewLogsOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Order logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function defines the button to the next the next screen for viewing
	 * performance logs for BMmanager
	 * 
	 * @param event
	 * @throws Exception throws error in loading java-fx
	 */
	@FXML
	public void getViewPerformanceLogsBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerViewLogsPerformanceForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Performance logs");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getExportLogsBtn(ActionEvent event) throws Exception {

	}

	/**
	 * This function defines the button to the previous screen for BMmanager
	 * 
	 * @param event
	 * @throws Exception throws error in loading java-fx
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

}
