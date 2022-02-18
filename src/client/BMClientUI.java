package client;

import javax.swing.JOptionPane;

import gui.IpFormController;
import gui.LoginFormController;

import javafx.application.Application;
import javafx.stage.Stage;

public class BMClientUI extends Application {
//	public static ClientController ClientController; // only one instance

	public static MainClient mainClient;

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainClient = new MainClient("localhost", 5555, this); 
		
		IpFormController aFrame = new IpFormController(); // create IpFrame
		aFrame.start(primaryStage);


	}

	public static void accept(Object msg) {
		mainClient.handleMessageFromClientUI(msg);
	}

	public static void display(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
