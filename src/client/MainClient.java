// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import logic.Customer;

import logic.Delivery;
import logic.Employer;
import logic.FileData;
import logic.Log;
import logic.Message;

import logic.MessageType;
import logic.Order;
import logic.Restaurant;

import ocsf.*;

import java.io.*;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class MainClient extends AbstractClient { // ChatClient in Ex
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */

	BMClientUI clientUI;
	public static String serverIP;
	public static boolean awaitResponse = false;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public MainClient(String host, int port, BMClientUI clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;

	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> handleMessageFrom Server");
		Message message = (Message) msg;
		awaitResponse = false;
		System.out.println(message.getMessageType());
		switch (message.getMessageType()) {

		case usersCheckExist:
			UserController.usersCheckExistFlag = (boolean) message.getMessageData();
			break;

		case IpAddress:
			UserController.ipAddress = (String) message.getMessageData();
			serverIP = UserController.ipAddress;
			break;

		case logIn:
			UserController.logInStatus = (String) message.getMessageData();
			break;

		case logOut:
			UserController.logOutStatus = (boolean) message.getMessageData();
			break;

		/// ********* get cases *********

		case getUserDetails:
			ArrayList<String> usersList = (ArrayList<String>) message.getMessageData();
			UserController.userID = usersList.get(0);
			UserController.firstName = usersList.get(1);
			UserController.lastName = usersList.get(2);
			UserController.address = usersList.get(3);
			UserController.email = usersList.get(4);
			UserController.phone = usersList.get(5);
			UserController.authorization = usersList.get(6);
			UserController.status = usersList.get(7);
			break;

		case getRestaurantDetails:
			ArrayList<String> restaurantsList = (ArrayList<String>) message.getMessageData();

			UserController.userID = restaurantsList.get(0);
			String[] parts = restaurantsList.get(1).split("_");
			String name = parts[0];
			String branch = parts[1];
			UserController.firstName = name;
			UserController.employeeName = restaurantsList.get(2);
			UserController.employeeCode = restaurantsList.get(3);
			UserController.address = branch;
			UserController.status = restaurantsList.get(4);
			UserController.authorization = restaurantsList.get(5);
			UserController.email = restaurantsList.get(6);
			UserController.phone = restaurantsList.get(7);
			break;

		case getCustomerDetails:
			ArrayList<String> customersList = (ArrayList<String>) message.getMessageData();
			UserController.userID = customersList.get(0);
			UserController.firstName = customersList.get(1);
			UserController.lastName = customersList.get(2);
			UserController.address = customersList.get(3);
			UserController.email = customersList.get(4);
			UserController.phone = customersList.get(5);
			UserController.authorization = customersList.get(6);
			UserController.accountType = customersList.get(7);
			UserController.status = customersList.get(8);
			UserController.w4c = customersList.get(9);
			UserController.employeeName = customersList.get(10);
			UserController.employeeCode = customersList.get(11);
			UserController.creditCard = customersList.get(12);
			UserController.billing = customersList.get(13);
			break;

		case getCustomersDetailsAsUsers:
			UserController.customersDetailsAsUsers = (ArrayList<Customer>) message.getMessageData();
			break;

		case getRestaurantDetailsAsUsers:
			UserController.restautantsDetailsAsUsers = (ArrayList<Restaurant>) message.getMessageData();
			break;

		case getAllRestaurantsFullName:
			OrderController.restaurants = (ArrayList<Restaurant>) message.getMessageData();
			break;

		case getAllRestaurants:
			OrderController.restaurants = (ArrayList<Restaurant>) message.getMessageData();
			break;

		case getMealsInRestaurant:
			OrderController.mealslist = (ArrayList<String>) message.getMessageData();
			break;

		case getMealPriceItems:
			OrderController.selectedPrice = (String) message.getMessageData();
			break;

		case getAllOrdersDetails:
			OrderController.allOrders = (ArrayList<Order>) message.getMessageData();
			break;

		case getOrderDetailsByIdAndOrderNum:
			OrderController.orders = (ArrayList<Order>) message.getMessageData();
			break;

		case getOrderDetailsByID:
			OrderController.ordersByID = (ArrayList<Order>) message.getMessageData();
			break;

		case getDeliveryDetails:
			OrderController.delivery = (ArrayList<Delivery>) message.getMessageData();
			break;

		case getRefundDetails:
			OrderController.refundList = (ArrayList<String>) message.getMessageData();
			break;

		case getAllCustomerDetails:
			UserController.customersList = (ArrayList<Customer>) message.getMessageData();
			break;

		case getAllRestaurantsDetails:
			UserController.restaurantsList = (ArrayList<Restaurant>) message.getMessageData();
			break;

		case getAllRequstionForBuisnesAccount:
			UserController.requests = (ArrayList<Customer>) message.getMessageData();
			break;

		case getEmployeeDetails:
			UserController.EmployerList = (ArrayList<Employer>) message.getMessageData();
			break;
	

		case getLogs:
			OrderController.logs = (ArrayList<Log>) message.getMessageData();
			break;

		/// ********* add cases *********

		case addOrder:
			OrderController.addOrderFlag = (boolean) message.getMessageData();
			break;

		case addDeliveryDetails:
			OrderController.addDeliveryDetailsFlag = (boolean) message.getMessageData();
			break;

		case addMealToMenu:
			OrderController.addMealToMenuFlag = (boolean) message.getMessageData();
			break;

		case addRefund:
			OrderController.addRefundFlag = (boolean) message.getMessageData();
			break;
			
		case addLogs:
			OrderController.addLogsFlag = (boolean) message.getMessageData();
			break;

		case registerNewEmployer:
			UserController.registerNewEmployerFlag = (boolean) message.getMessageData();
			break;

		/// ********* change cases *********

		case changeCustomerAuthorization:
			UserController.changeCustomerAuthorizationFlag = (boolean) message.getMessageData();
			break;

		case changeRestaurantAuthorization:
			UserController.changeRestaurantAuthorizationFlag = (boolean) message.getMessageData();
			break;

		case changeCustomerStatus:
			UserController.changeCustomerStatusFlag = (boolean) message.getMessageData();
			break;
			
		case updatePrivateAccount:
			UserController.updatePrivateAccountFlag = (boolean) message.getMessageData();
			break;

		case changeRestaurantStatus:
			UserController.changeRestaurantStatusFlag = (boolean) message.getMessageData();
			break;

		case changeOrderStatus:
			UserController.changeOrderStatusFlag = (boolean) message.getMessageData();
			break;

		case changeOrderApproveTime:
			OrderController.changeOrderApproveTimeFlag = (boolean) message.getMessageData();
			break;

		case confirmBusinessAccount:
			UserController.confirmBusinessAccountFlag = (boolean) message.getMessageData();

			break;

		case UpdateMealsPriceInMenu:

			OrderController.updateMealsPriceInMenuFlag = (boolean) message.getMessageData();

			break;

		case UpdateAveragePrepareTime:

			OrderController.updateAveragePrepareTimeFlag = (boolean) message.getMessageData();

			break;

		case UpdateRefundDetails:
			OrderController.updateRefundDetailsFlag = (boolean) message.getMessageData();
			break;

		case changeEmployerStatus:
			UserController.changeEmployerStatusFlag = (boolean) message.getMessageData();
			break;

		case DeleteMealFromMenu:
			OrderController.deleteMealFromMenuFlag = (boolean) message.getMessageData();
			break;

		case sendFile:
			System.out.println("File was sent to CEO");
			break;


		case getFiles:
			OrderController.allFiles = (ArrayList<FileData>) message.getMessageData();
			break;

		default:
			BMClientUI.display("Could not read message from server");
		}

	}

	public static String getServerIP() {
		return serverIP;
	}

	// System.out.println(" " + );

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(Object message) // changed to object
	{
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			BMClientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println("Error in closing connection");
		}
		System.exit(0);
	}
}
// End of ChatClient class
