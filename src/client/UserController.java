package client;

import java.util.ArrayList;

import logic.Customer;
import logic.Employer;
import logic.Message;
import logic.MessageType;
import logic.Restaurant;

import logic.UserManagment;
import javafx.stage.Stage;

/**
 * 
 * Class to handles all requests regarding to the user, Additional to our
 * Package diagram.
 * Requests identified by the name of the request
 *
 */
public class UserController {
	/** Current stage of the user */
	public static Stage currentStage;

	/** ArrayList from type Customer to save the all the customers */
	public static ArrayList<Customer> customersList;
	/** ArrayList from type Customer to save the all the requested customers */
	public static ArrayList<Customer> requests;
	/**
	 * ArrayList from type Customer to save the all the customers details as USERS
	 */
	public static ArrayList<Customer> customersDetailsAsUsers;

	/** ArrayList from type Restaurant to save the all the restaurants */
	public static ArrayList<Restaurant> restaurantsList;
	/**
	 * ArrayList from type Restaurant to save the all the restaurants details as
	 * USERS
	 */
	public static ArrayList<Restaurant> restautantsDetailsAsUsers;

	/** ArrayList from type String to save ID and autorization for users */
	public static ArrayList<String> IDAndAutorization;

	/** ArrayList from type Employer to save the all the employees */
	public static ArrayList<Employer> EmployerList = new ArrayList<Employer>();

	/** Save selected user ID throw login process */
	public static String userID;
	/** Save selected customer W4C throw login process */
	public static String w4c;
	/** Save selected user userName throw login process */
	public static String userName;
	/** Save selected user password throw login process */
	public static String password;
	/** Save selected user firstName throw login process */
	public static String firstName;
	/** Save selected user lastName throw login process */
	public static String lastName;
	/** Save selected user address throw login process */
	public static String address;
	/** Save selected user email throw login process */
	public static String email;
	/** Save selected user throw login process */
	public static String authorization;
	/** Save selected user account type throw login process */
	public static String accountType;
	/** Save selected user phone throw login process */
	public static String phone;
	/** Save selected user status throw login process */
	public static String status;
	/** Save selected user employee name throw login process */
	public static String employeeName;
	/** Save selected user employee code throw login process */
	public static String employeeCode;
	/** Save selected user credit card throw login process */
	public static String creditCard;
	/** Save selected user billing throw login process */
	public static String billing;

	/** Save selected ipAddress throw connection process */
	public static String ipAddress;
	/** Save selected login status throw login process */
	public static String logInStatus;
	/** Flag for logout check */
	public static boolean logOutStatus;

	/** Flag for user check exists check */
	public static boolean usersCheckExistFlag;
	/** Flag for change customer authorization check */
	public static boolean changeCustomerAuthorizationFlag;
	/** Flag for change restaurant authorization check */
	public static boolean changeRestaurantAuthorizationFlag;

	/** Flag for W4C scan check */
	public static boolean W4CScanFlag = false;

	/** Flag for change customer status check */
	public static boolean changeCustomerStatusFlag;
	/** Flag for change restaurant status check */
	public static boolean changeRestaurantStatusFlag;

	/** Flag for confirm business account check */
	public static boolean confirmBusinessAccountFlag;
	/** Flag for change order status check */
	public static boolean changeOrderStatusFlag;
	/** Flag for change employer status check */
	public static boolean changeEmployerStatusFlag;
	/** Flag for update private account check */
	public static boolean updatePrivateAccountFlag;
	/** Flag for register new employer check */
	public static boolean registerNewEmployerFlag;

	public static boolean usersCheckExist() {
		Message msg = new Message(MessageType.usersCheckExist, null);
		BMClientUI.accept(msg);
		return usersCheckExistFlag;
	}

	public static String IpAddress(String ipAddress) {
		Message msg = new Message(MessageType.IpAddress, ipAddress);
		BMClientUI.accept(msg);
		return ipAddress;
	}

	public static String login(ArrayList<String> items) {
		Message msg = new Message(MessageType.logIn, items);
		BMClientUI.accept(msg);
		return logInStatus;
	}

	public static boolean logout(String username) {
		Message msg = new Message(MessageType.logOut, userName);
		BMClientUI.accept(msg);
		return logOutStatus;
	}

	public static void getUserDetails(String username, String password) {
		UserManagment currentLogIn = new UserManagment(username, password);
		Message msg = new Message(MessageType.getUserDetails, currentLogIn);
		BMClientUI.accept(msg);

	}

	public static ArrayList<Restaurant> getRestaurantDetails(String username, String password) {
		UserManagment currentLogIn = new UserManagment(username, password);
		Message msg = new Message(MessageType.getRestaurantDetails, currentLogIn);
		BMClientUI.accept(msg);
		return restaurantsList;
	}

	public static ArrayList<Customer> getCustomerDetails(String username, String password) {
		UserManagment currentLogIn = new UserManagment(username, password);
		Message msg = new Message(MessageType.getCustomerDetails, currentLogIn);
		BMClientUI.accept(msg);
		return customersList;
	}

	public static ArrayList<Customer> getCustomersDetailsAsUsers() {
		Message msg = new Message(MessageType.getCustomersDetailsAsUsers, null);
		BMClientUI.accept(msg);
		return customersDetailsAsUsers;

	}

	public static ArrayList<Restaurant> getRestaurantDetailsAsUsers() {
		Message msg = new Message(MessageType.getRestaurantDetailsAsUsers, null);
		BMClientUI.accept(msg);
		return restautantsDetailsAsUsers;

	}

	public static boolean changeCustomerAuthorization(ArrayList<String> items) {
		Message msg = new Message(MessageType.changeCustomerAuthorization, items);
		BMClientUI.accept(msg);
		return changeCustomerAuthorizationFlag;

	}

	public static boolean changeRestaurantAuthorization(ArrayList<String> items) {
		Message msg = new Message(MessageType.changeRestaurantAuthorization, items);
		BMClientUI.accept(msg);
		return changeRestaurantAuthorizationFlag;

	}

	public static ArrayList<Customer> getAllCustomerDetails(String customerString) {
		Message msg = new Message(MessageType.getAllCustomerDetails, customerString);
		BMClientUI.accept(msg);
		return customersList;
	}

	public static ArrayList<Restaurant> getAllRestaurantsDetails(String restaurantString) {
		Message msg = new Message(MessageType.getAllRestaurantsDetails, restaurantString);
		BMClientUI.accept(msg);
		return restaurantsList;
	}

	public static boolean changeCustomerStatus(ArrayList<String> getUserItems) {
		Message msg = new Message(MessageType.changeCustomerStatus, getUserItems);
		BMClientUI.accept(msg);
		return changeCustomerStatusFlag;
	}

	public static boolean changeRestaurantStatus(ArrayList<String> getUserItems) {
		Message msg = new Message(MessageType.changeRestaurantStatus, getUserItems);
		BMClientUI.accept(msg);
		return changeRestaurantStatusFlag;
	}

	public static ArrayList<Customer> getAllRequstionForBuisnesAccount() {
		Message msg = new Message(MessageType.getAllRequstionForBuisnesAccount, null);
		BMClientUI.accept(msg);
		return requests;
	}

	public static ArrayList<Employer> getEmployeeDetails(String status) {
		Message msg = new Message(MessageType.getEmployeeDetails, status);
		BMClientUI.accept(msg);
		return EmployerList;
	}

	public static boolean confirmBuisnesAccount(ArrayList<String> userItems) {
		Message msg = new Message(MessageType.confirmBusinessAccount, userItems);
		BMClientUI.accept(msg);
		return confirmBusinessAccountFlag;
	}

	public static boolean changeOrderStatus(ArrayList<String> getOrderDetailsItems) {
		Message msg = new Message(MessageType.changeOrderStatus, getOrderDetailsItems);
		BMClientUI.accept(msg);
		return changeOrderStatusFlag;
	}

	public static boolean updatePrivateAccount(ArrayList<String> items) {
		Message msg = new Message(MessageType.updatePrivateAccount, items);
		BMClientUI.accept(msg);
		return updatePrivateAccountFlag;
	}

	public static boolean registerNewEmployer(String employerCode, String employerName) {
		Employer newEmpl = new Employer(employerCode, employerName);
		Message msg = new Message(MessageType.registerNewEmployer, newEmpl);
		BMClientUI.accept(msg);
		return registerNewEmployerFlag;
	}

	public static boolean changeEmployerStatus(ArrayList<String> getItems) {
		Message msg = new Message(MessageType.changeEmployerStatus, getItems);
		BMClientUI.accept(msg);
		return changeEmployerStatusFlag;

	}

}
