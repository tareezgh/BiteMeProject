package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logic.Client;
import server.MainServer;

public class ClientsListFormController implements Initializable {

	@FXML
	private Button DoneBtn;

	@FXML
	private Label lblMessage;

	@FXML
	private TableView<Client> tbl = new TableView<Client>();
	@FXML
	private TableColumn<Client, String> nameColumn; 
	@FXML
	private TableColumn<Client, String> addressColumn; 
	@FXML
	private TableColumn<Client, String> statusColumn; 

	//
	@FXML
	private ListView<String> list;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<Client> clientList = MainServer.clientItems;
		ObservableList<Client> data = FXCollections.observableArrayList(clientList);

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("hostName"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("clientIp"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("clientConnected"));

		tbl.setItems(data);

	}

	@FXML
	void getDoneBtn(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide(); // hide main window

	}

	@FXML
	void getDisConnectBtn(ActionEvent event) { // ****** 
		

	}
	
}
