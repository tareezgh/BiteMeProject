package server;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;

// This file contains material supporting section 3.7 of the textbook:

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import DB.BMManagerDBController;
import DB.DBConnector;
import DB.UserDBController;
import common.MyFile;
import logic.Client;
import logic.Customer;
import logic.Delivery;
import logic.Employer;
import logic.FileData;
import logic.Log;
import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.Restaurant;
import logic.UserManagment;
import ocsf.AbstractServer;
import ocsf.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class MainServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	Message msgFromServer = null;
	public static DBConnector dbConnector;

	public static String serverIP;
	public static String clientIp;
	public static String hostName;
	public static String clientConnected = "Not Connected";

	public static ArrayList<Client> clientItems = new ArrayList<Client>(); 

	public static ArrayList<Customer> customers = new ArrayList<Customer>(); 
	public static ArrayList<Customer> requstedCustomers = new ArrayList<Customer>();

	public static ArrayList<String> mealsInRestaurantlist = new ArrayList<String>();
	public static ArrayList<String> refunds = new ArrayList<String>();

	public static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static ArrayList<Order> ordersByID = new ArrayList<Order>();
	public static ArrayList<Order> allOrders = new ArrayList<Order>();

	public static ArrayList<Log> logs = new ArrayList<Log>();
	public static ArrayList<FileData> allFiles = new ArrayList<FileData>();

	public static ArrayList<Delivery> delivery = new ArrayList<Delivery>();

	public static ArrayList<Employer> employee = new ArrayList<Employer>();

	int clientNum = 0;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public MainServer(int port) {
		super(port);
//		dbConnector = new DBConnector(); /// ***
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("google.com", 80));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		serverIP = socket.getLocalAddress().toString();
		serverIP = serverIP.substring(1);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("--> handleMessageFrom Client");
		Message message = (Message) msg;
		System.out.println(message.getMessageType());
		switch (message.getMessageType()) {

		case usersCheckExist:
			boolean usersCheckExistFlag = UserDBController.usersCheckExist();
			msgFromServer = new Message(MessageType.usersCheckExist, usersCheckExistFlag);
			break;

		case IpAddress:
			clientNum++;
			clientConnected = "connected";
			clientIp = client.getInetAddress().getHostAddress();
			hostName = client.getInetAddress().getHostName();
			Client clientList = new Client(hostName, clientIp, clientConnected);
			clientItems.add(clientList);
			clientConnected(client);
			msgFromServer = new Message(MessageType.IpAddress, serverIP); 
			break;

		case logIn:
			String logInStatus = UserDBController.tryToConnect((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.logIn, logInStatus);
			break;

		case logOut:
			boolean logOutFlag = UserDBController.removeUserFromLoginArr((String) message.getMessageData());
			msgFromServer = new Message(MessageType.logOut, logOutFlag);
			break;

		/// ********* get cases *********

		case getUserDetails:
			ArrayList<String> userssList = UserDBController.getUserDetails((UserManagment) message.getMessageData());
			msgFromServer = new Message(MessageType.getUserDetails, userssList);
			break;

		case getCustomerDetails:
			ArrayList<String> customersList = UserDBController
					.getCustomerDetails((UserManagment) message.getMessageData());
			msgFromServer = new Message(MessageType.getCustomerDetails, customersList);
			break;

		case getRestaurantDetails:
			ArrayList<String> restaurantList = UserDBController
					.getRestaurantDetails((UserManagment) message.getMessageData());
			msgFromServer = new Message(MessageType.getRestaurantDetails, restaurantList);
			break;

		case getCustomersDetailsAsUsers:
			ArrayList<Customer> customersListAsUsers = UserDBController
					.getCustomersDetailsAsUsers((ArrayList<Customer>) message.getMessageData());
			msgFromServer = new Message(MessageType.getCustomersDetailsAsUsers, customersListAsUsers);
			break;

		case getRestaurantDetailsAsUsers:
			ArrayList<Restaurant> restaurantListAsUsers = UserDBController
					.getRestaurantDetailsAsUsers((ArrayList<Restaurant>) message.getMessageData());
			msgFromServer = new Message(MessageType.getRestaurantDetailsAsUsers, restaurantListAsUsers);
			break;

		case getAllRestaurants:
			restaurants = DB.OrderDBController.getRestaurantDetails();
			msgFromServer = new Message(MessageType.getAllRestaurants, restaurants);
			break;

		case getMealsInRestaurant:
			mealsInRestaurantlist = DB.OrderDBController
					.getMealsInRestaurant((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getMealsInRestaurant, mealsInRestaurantlist);
			break;

		case getMealPriceItems:
			String mealPrice = DB.OrderDBController.getMealPrice((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getMealPriceItems, mealPrice);
			break;

		case getAllOrdersDetails:
			allOrders = DB.OrderDBController.getAllOrderDetails();
			msgFromServer = new Message(MessageType.getAllOrdersDetails, allOrders);
			break;

		case getOrderDetailsByIdAndOrderNum:
			orders = DB.OrderDBController.getOrderDetailsByIdAndOrderNum((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getOrderDetailsByIdAndOrderNum, orders);
			break;

		case getOrderDetailsByID:
			ordersByID = DB.OrderDBController.getOrderDetailsByID((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getOrderDetailsByID, ordersByID);
			break;

		case getDeliveryDetails:
			delivery = DB.OrderDBController.getDeliveryDetails((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getDeliveryDetails, delivery);
			break;

		case getRefundDetails:
			refunds = DB.OrderDBController.getRefundDetails((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getRefundDetails, refunds);
			break;
			
		case getAllRestaurantsFullName:
			restaurants = DB.OrderDBController.getAllRestaurantsFullName();
			msgFromServer = new Message(MessageType.getAllRestaurantsFullName, restaurants);
			break;

		case getAllCustomerDetails:
			customers = BMManagerDBController.getAllCustomerDetails((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getAllCustomerDetails, customers);
			break;

		case getAllRestaurantsDetails:
			restaurants = BMManagerDBController.getAllRestaurantsDetails((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getAllRestaurantsDetails, restaurants);
			break;

		case getAllRequstionForBuisnesAccount:
			requstedCustomers = BMManagerDBController.getAllRequstionForBuisnesAccount();
			msgFromServer = new Message(MessageType.getAllRequstionForBuisnesAccount, requstedCustomers);
			break;

		case getEmployeeDetails:
			employee = BMManagerDBController.getEmployeeDetails((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getEmployeeDetails, employee);
			break;

		case getLogs:
			logs = BMManagerDBController.getLogs();
			msgFromServer = new Message(MessageType.getLogs, logs);
			break;

		/// ********* add cases *********
		case addLogs:
			Boolean addLogsFlag = BMManagerDBController.addLogs((ArrayList<Log>) message.getMessageData());
			msgFromServer = new Message(MessageType.addLogs, addLogsFlag);
			break;

		case addOrder:
			Boolean addOrderFlag = DB.OrderDBController.addOrder((Order) message.getMessageData());
			msgFromServer = new Message(MessageType.addOrder, addOrderFlag);
			break;

		case addDeliveryDetails:
			Boolean addDeliveryDetailsFlag = DB.OrderDBController
					.addDeliveryDetails((Delivery) message.getMessageData());
			msgFromServer = new Message(MessageType.addDeliveryDetails, addDeliveryDetailsFlag);
			break;

		case addRefund:
			Boolean addRefundFlag = DB.OrderDBController.addRefund((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.addRefund, addRefundFlag);
			break;
			
		case addMealToMenu:
			Boolean addMealToMenuFlag = DB.RestaurantDBController
					.addMealToMenu((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.addMealToMenu, addMealToMenuFlag);
			break;

		case registerNewEmployer:
			Employer newEmpl = (Employer) message.getMessageData();
			Boolean registerNewEmployerFlag = BMManagerDBController.insertNewEmployer(newEmpl.getEmployerCode(),
					newEmpl.getEmployerName());
			msgFromServer = new Message(MessageType.registerNewEmployer, registerNewEmployerFlag);
			break;

		/// ********* change cases *********

		case changeCustomerAuthorization:
			Boolean changeCustomerAuthorizationFlag = BMManagerDBController
					.changeCustomerAuthorization((ArrayList<String>) message.getMessageData());
		
			msgFromServer = new Message(MessageType.changeCustomerAuthorization, changeCustomerAuthorizationFlag);

			break;

		case changeRestaurantAuthorization:
			Boolean changeRestaurantAuthorizationFlag = BMManagerDBController
					.changeRestaurantAuthorization((ArrayList<String>) message.getMessageData());
			
			msgFromServer = new Message(MessageType.changeRestaurantAuthorization, changeRestaurantAuthorizationFlag);
			break;

		case changeCustomerStatus:
			Boolean changeCustomerStatusFlag = BMManagerDBController
					.changeCustomerStatus((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.changeCustomerStatus, changeCustomerStatusFlag);
			break;

		case changeRestaurantStatus:
			Boolean changeRestaurantStatusFlag = BMManagerDBController
					.changeRestaurantStatus((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.changeRestaurantStatus, changeRestaurantStatusFlag);
			break;

		case confirmBusinessAccount:
			Boolean confirmBusinessAccountFlag = BMManagerDBController
					.confirmBusinessAccount((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.confirmBusinessAccount, confirmBusinessAccountFlag);
			break;
			
		case updatePrivateAccount:
			Boolean updatePrivateAccountFlag = BMManagerDBController
					.updatePrivateAccount((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.updatePrivateAccount, updatePrivateAccountFlag);
			break;

		case UpdateMealsPriceInMenu:
			Boolean UpdateMealsPriceInMenuFlag = DB.RestaurantDBController
					.UpdateMealsPriceInMenu((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.UpdateMealsPriceInMenu, UpdateMealsPriceInMenuFlag);
			break;
			
		case changeOrderStatus:
			Boolean changeOrderStatusFlag = DB.RestaurantDBController
					.changeOrderStatus((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.changeOrderStatus, changeOrderStatusFlag);
			break;

		case changeOrderApproveTime:
			Boolean changeOrderApproveTimeFlag = DB.RestaurantDBController
					.changeOrderApproveTime((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.changeOrderApproveTime, changeOrderApproveTimeFlag);
			break;
			
		case DeleteMealFromMenu:
			Boolean DeleteMealFromMenuFlag = DB.RestaurantDBController
					.DeleteMealFromMenu((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.DeleteMealFromMenu, DeleteMealFromMenuFlag);
			break;

		case UpdateAveragePrepareTime:
			Boolean UpdateAveragePrepareTimeFlag = DB.OrderDBController
					.UpdateAveragePrepareTime((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.UpdateAveragePrepareTime, UpdateAveragePrepareTimeFlag);
			break;

		case UpdateRefundDetails:
			Boolean UpdateRefundDetailsFlag = DB.OrderDBController
					.UpdateRefundDetails((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.UpdateRefundDetails, UpdateRefundDetailsFlag);
			break;

		case changeEmployerStatus:
			Boolean changeEmployerStatusFlag = BMManagerDBController
					.changeEmployerStatus((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.changeEmployerStatus, changeEmployerStatusFlag);
			break;

		

		case getFiles:
			allFiles = BMManagerDBController.getFiles();
			msgFromServer = new Message(MessageType.getFiles, allFiles);
			break;

		case sendFile:
			MyFile saveFile = (MyFile) message.getMessageData();
			try {
				BMManagerDBController.updateFile(saveFile);
			} catch (SerialException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;

		default:
			msgFromServer = new Message(MessageType.Error, null);
		}

		try {
			client.sendToClient(msgFromServer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		clientConnected = "connected";
		clientIp = client.getInetAddress().getHostAddress();
		hostName = client.getInetAddress().getHostName();
		System.out.println(client + " connected!");

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		super.serverStarted(); 
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		
		System.out.println("Server has stopped listening for connections.");
	}

}
//End of EchoServer class
