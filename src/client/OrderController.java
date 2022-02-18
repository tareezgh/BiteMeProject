package client;

import java.util.ArrayList;

import logic.Delivery;

import logic.FileData;
import logic.Log;

import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.Restaurant;

/**
 * Class to handles all requests regarding to the order, Additional to our
 * Package diagram.
 * Requests identified by the name of the request
 */
public class OrderController {
	/** ArrayList from type Order to save the all meals throw the order process */
	public static ArrayList<Order> ordersList = new ArrayList<Order>();
	/**
	 * ArrayList from type Order to save orders by specific customer's ID number and
	 * order number
	 */
	public static ArrayList<Order> orders;
	/** ArrayList from type Order to save orders by specific customer's ID number */
	public static ArrayList<Order> ordersByID;
	/** ArrayList from type Order to save orders from DB */
	public static ArrayList<Order> allOrders;

	/**
	 * ArrayList from type Delivery to save the delivery details throw the order
	 * process
	 */
	public static ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
	/** ArrayList from type Delivery to save all delivery details from DB */
	public static ArrayList<Delivery> delivery;

	/** ArrayList from type Restaurant to save all restaurants details from DB */
	public static ArrayList<Restaurant> restaurants;

	/** ArrayList from type String to save meals list from DB */
	public static ArrayList<String> mealslist;
	/** ArrayList from type String refund details from DB */
	public static ArrayList<String> refundList = new ArrayList<String>();

	/** ArrayList from type Log to save all Log details from DB */
	public static ArrayList<Log> logs;
	/** ArrayList from type FileData to save all FileData details from DB */
	public static ArrayList<FileData> allFiles = new ArrayList<FileData>();

	// For Choose restaurant
	/** Save selected restaurant name throw the order process */
	public static String selectedRestaurantName;
	/** Save selected restaurant branch throw the order process */
	public static String selectedRestaurantBranch;

	// Menu
	/** Save selected category of meal throw the order process */
	public static String selectedCategory;
	/** Save selected meal throw the order process */
	public static String selectedMeal;
	/** Save selected meal size throw the order process */
	public static String selectedSize;
	/** Save selected level of cook of meal throw the order process */
	public static String selectedLvlOfCook;
	/** Save selected meal restrictions throw the order process */
	public static String selectedRestrictions;
	/** Save selected meal price throw the order process */
	public static String selectedPrice;

	/** Save total price throw the order process */
	public static int totalPrice = 0;
	/** Save total price if the customer have refund throw the order process */
	public static int totalPriceForRefund;
	/** Save meal number throw the order process */
	public static int mealNum = 0;

	// Delivery
	/** Save selected type (TAKE-AWAY OR DELIVERY) throw the order process */
	public static String selectedType;
	/**
	 * Save selected type of payment (PRIVATE OR BUISNESS) throw the order process
	 */
	public static String selectedTypeOfPayment;
	/**
	 * Save selected deliveryType (REGULAR, SHARED OR ROBOT) throw the order process
	 */
	public static String selecteDeliveryType;
	/** Save selected order time throw the order process */
	public static String selectedTime;
	/** Save selected order date throw the order process */
	public static String selectedDate;

	/** Save selected customer name throw the order process */
	public static String selectedCustomerName;
	/** Save selected customer phone throw the order process */
	public static String selectedCustomerPhone;
	/** Save selected customer phone throw the order process */
	public static String selectedAddress;
	/** Save selected employee name throw the order process for the customer */
	public static String selectedEmployeeName;
	/** Save selected employee code throw the order process for the customer */
	public static String selectedEmployeeCode;
	/** Save selected participation number throw the order process */
	public static String particepationNumber;

	/** Order number counter start from 1 */
	public static int orderNumber = 1;
	/** Delivery number counter start from 1 */
	public static int deliveryNum = 1;

	/** System current date */
	public static String currentDate;
	/** System current time */
	public static String currentTime;

	/** Approve time when restaurant approve order */
	public static String approveTime;
	/** Pick time when customer pick up the order */
	public static String pickTime;

	/** Flag for discount check */
	public static boolean discountFlag;
	/** Flag for the type shared order check */
	public static boolean sharedFlag = false;

	/** Flag for add meal check */
	public static boolean addFlag = false;
	/** Flag for add order check */
	public static boolean addOrderFlag;
	/** Flag for adding delivery details check */
	public static boolean addDeliveryDetailsFlag;
	/** Flag for add meal to menu check */
	public static boolean addMealToMenuFlag;
	/** Flag for add refund check */
	public static boolean addRefundFlag;
	/** Flag for add logs check */
	public static boolean addLogsFlag;

	/** Flag for change order approve time check */
	public static boolean changeOrderApproveTimeFlag;
	/** Flag for change meal price in menu check */
	public static boolean updateMealsPriceInMenuFlag;
	/** Flag for delete meal from menu check */
	public static boolean deleteMealFromMenuFlag;
	/** Flag for update average preparing time check */
	public static boolean updateAveragePrepareTimeFlag;
	/** Flag for update refund details check */
	public static boolean updateRefundDetailsFlag;

	public static ArrayList<Restaurant> getAllRestaurants(String messageData) {
		Message msg = new Message(MessageType.getAllRestaurants, restaurants);
		BMClientUI.accept(msg);
		return restaurants;
	}

	public static ArrayList<String> getMealsInRestaurant(ArrayList<String> getMealsInRestaurantItems) {
		Message msg = new Message(MessageType.getMealsInRestaurant, getMealsInRestaurantItems);
		BMClientUI.accept(msg);
		return mealslist;
	}

	public static String getMealsInRestaurantPrice(ArrayList<String> getMealPriceItems) {
		Message msg = new Message(MessageType.getMealPriceItems, getMealPriceItems);
		BMClientUI.accept(msg);
		return selectedPrice;
	}

	public static ArrayList<Order> getAllOrderDetails(ArrayList<String> Items) {
		Message msg = new Message(MessageType.getAllOrdersDetails, Items);
		BMClientUI.accept(msg);
		return allOrders;
	}

	public static ArrayList<Order> getOrderDetailsByIdAndOrderNum(ArrayList<String> Items) {
		Message msg = new Message(MessageType.getOrderDetailsByIdAndOrderNum, Items);
		BMClientUI.accept(msg);
		return orders;
	}

	public static ArrayList<Order> getOrderDetailsByID(String userID) { // ***
		Message msg = new Message(MessageType.getOrderDetailsByID, userID);
		BMClientUI.accept(msg);
		return ordersByID;
	}

	public static ArrayList<Delivery> getDeliveryDetails(String userID) {
		Message msg = new Message(MessageType.getDeliveryDetails, userID);
		BMClientUI.accept(msg);
		return delivery;
	}

	public static ArrayList<String> getRefundDetails(ArrayList<String> items) {
		Message msg = new Message(MessageType.getRefundDetails, items);
		BMClientUI.accept(msg);
		return refundList;
	}

	// ******** ADD ********

	public static boolean addOrder(Order order) {
		Message msg = new Message(MessageType.addOrder, order);
		BMClientUI.accept(msg);
		return addOrderFlag;

	}

	public static boolean addDeliveryDetails(Delivery delivery) {
		Message msg = new Message(MessageType.addDeliveryDetails, delivery);
		BMClientUI.accept(msg);
		return addDeliveryDetailsFlag;
	}

	public static boolean addMealToMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.addMealToMenu, items);
		BMClientUI.accept(msg);
		return addMealToMenuFlag;

	}

	public static boolean addRefund(ArrayList<String> items) {
		Message msg = new Message(MessageType.addRefund, items);
		BMClientUI.accept(msg);
		return addRefundFlag;
	}

	public static boolean UpdateMealsPriceInMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.UpdateMealsPriceInMenu, items);
		BMClientUI.accept(msg);
		return updateMealsPriceInMenuFlag;

	}

	public static boolean UpdateAveragePrepareTime(ArrayList<String> items) {
		Message msg = new Message(MessageType.UpdateAveragePrepareTime, items);
		BMClientUI.accept(msg);
		return updateAveragePrepareTimeFlag;

	}

	public static boolean UpdateRefundDetails(ArrayList<String> items) {
		Message msg = new Message(MessageType.UpdateRefundDetails, items);
		BMClientUI.accept(msg);
		return updateRefundDetailsFlag;
	}

	public static boolean DeleteMealFromMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.DeleteMealFromMenu, items);
		BMClientUI.accept(msg);
		return deleteMealFromMenuFlag;
	}

	public static ArrayList<Restaurant> getAllRestaurantsFullName(Object object) {
		Message msg = new Message(MessageType.getAllRestaurantsFullName, restaurants);
		BMClientUI.accept(msg);
		return restaurants;
	}

	public static ArrayList<Log> getLogs(String messageData) {
		Message msg = new Message(MessageType.getLogs, logs);
		BMClientUI.accept(msg);
		return logs;
	}

	public static boolean changeOrderApproveTime(ArrayList<String> items) {
		Message msg = new Message(MessageType.changeOrderApproveTime, items);
		BMClientUI.accept(msg);
		return changeOrderApproveTimeFlag;

	}

	public static ArrayList<FileData> getFiles(String messageData) {
		Message msg = new Message(MessageType.getFiles, allFiles);
		BMClientUI.accept(msg);
		return allFiles;
	}

}
