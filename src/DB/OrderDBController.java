package DB;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Delivery;

import logic.Order;
import logic.Restaurant;

/**
 * /**
 *
 * Class to handles all requests from client to access the DB,Here the requests
 * that relevant for Order requests. Additional to our Package diagram. Requests
 * Requests identified by the name of the request
 *
 */
public class OrderDBController {
	static ResultSet rs;

	// Customer
	public static ArrayList<Restaurant> getRestaurantDetails() {
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();
		String sqlQuery = "SELECT RestaurantName_Branch FROM restaurant";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					String[] details = rs.getString(1).split("_");
					String restaurantName = details[0];
					String restaurantBranch = details[1];

					Restaurant restaurantDetails = new Restaurant(restaurantName, restaurantBranch);

					restaurantsList.add(restaurantDetails);

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantsList;

	}

	public static ArrayList<Restaurant> getAllRestaurantsFullName() {
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();
		String sqlQuery = "SELECT RestaurantName_Branch FROM restaurant";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					String[] details = rs.getString(1).split("_");
					String restaurantName = details[0];
					String restaurantBranch = details[1];

					Restaurant restaurantDetails = new Restaurant(rs.getString(1), restaurantBranch);

					restaurantsList.add(restaurantDetails);

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantsList;

	}

	// Customer
	public static ArrayList<String> getMealsInRestaurant(ArrayList<String> items) {

		ArrayList<String> mealsList = new ArrayList<String>();
		String restaurantName = items.get(0);
		String category = items.get(1);

		String sqlQuery = "SELECT DISTINCT Meal FROM mealinrestaurant WHERE Restaurant = '" + restaurantName
				+ "' AND Category ='" + category + "';";
		String mealName = "";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					mealName = rs.getString("Meal");
					mealsList.add(mealName);
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mealsList;

	}

	// Customer
	public static String getMealPrice(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String mealName = items.get(1);
		String mealSize = items.get(2);
		String key = restaurantName + "_" + mealName + "_" + mealSize;
		System.out.println("Key " + key);
		String sqlQuery = "SELECT MealPrice FROM menu WHERE MealCode = '" + key + "';";
		String mealPrice = "";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					mealPrice = rs.getString("MealPrice");
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mealPrice;

	}

	// Customer
	public static ArrayList<Order> getAllOrderDetails() {
		ArrayList<Order> orderList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.order;";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
							rs.getString(15));
					orderList.add(orderDetails);
				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	// Customer
	public static ArrayList<Order> getOrderDetailsByIdAndOrderNum(ArrayList<String> items) { // items = UserID,OrderNum
		ArrayList<Order> orderList = new ArrayList<>();

		String userID = items.get(0);
		String orderNum = items.get(1);

		String selectQuery = "SELECT * FROM test.order WHERE customerID = '" + userID + "'AND OrderNumber ='" + orderNum
				+ "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
							rs.getString(15));

					orderList.add(orderDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	// Customer
	public static ArrayList<Order> getOrderDetailsByID(String UserID) {
		ArrayList<Order> orderList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.order WHERE customerID = '" + UserID + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
							rs.getString(15));

					orderList.add(orderDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	// Customer
	public static ArrayList<Delivery> getDeliveryDetails(String UserID) {
		ArrayList<Delivery> deliveryList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.delivery WHERE CustomerID = '" + UserID + "';";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Delivery deliveryDetails = new Delivery(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
							rs.getString(14), rs.getString(15));

					deliveryList.add(deliveryDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deliveryList;

	}

	// Customer
	public static ArrayList<String> getRefundDetails(ArrayList<String> items) {
		ArrayList<String> refundList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.refund;";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					String Key = rs.getString(1) + "_" + rs.getString(2);
					refundList.add(Key);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return refundList;
	}

	// ***** ADD SQL *****

	// Customer
	public static boolean addOrder(Order order) {

		String sqlQuery = "INSERT INTO test.order values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, order.getCustomerID() + "_" + order.getOrderNumber() + "_" + order.getMealNum()); // ******
				pst.setString(2, order.getOrderNumber());
				pst.setString(3, order.getCustomerID());
				pst.setString(4, order.getRestaurantName() + "_" + order.getMeal());
				pst.setString(5, order.getCategory());
				pst.setString(6, order.getSize());
				pst.setString(7, order.getLvlOfCook());
				pst.setString(8, order.getRestrictions());
				pst.setString(9, order.getStatus());
				pst.setString(10, order.getMealPrice());
				pst.setString(11, order.getRestaurantName() + "_" + order.getRestaurantBranch());
				pst.setString(12, order.getRestaurantBranch());
				pst.setString(13, " "); // Add when delivery done
				pst.setString(14, "0"); // Add when pick the order
				pst.setString(15, " "); // Add when pick the order
				pst.executeUpdate();

				pst.close();
				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return false;
	}

	// Customer
	public static boolean addDeliveryDetails(Delivery delivery) {
		boolean addDeliveryFlag = false;
		boolean addOrderDateFlag = false;
		String sqlQuery = "INSERT INTO test.delivery values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1,
						delivery.getCustomerID() + "_" + delivery.getDeliveryNum() + "_" + delivery.getOrderNum());

				pst.setString(2, delivery.getCustomerID());
				pst.setString(3, delivery.getOrderNumber());
				pst.setString(4, delivery.getTypeOfPayment());
				pst.setString(5, delivery.getTypeOfDelivery());
				pst.setString(6, delivery.getTime());
				pst.setString(7, delivery.getDate());
				pst.setString(8, delivery.getCustomerName());
				pst.setString(9, delivery.getPhoneNum());
				pst.setString(10, delivery.getAddress());
				pst.setString(11, delivery.getEmployeeName());
				pst.setString(12, delivery.getEmployeeCode());
				pst.setString(13, delivery.getShipmentTime());
				pst.setString(14, delivery.getPrice());
				pst.setString(15, delivery.getBasePrice());

				pst.executeUpdate();

				pst.close();
				addDeliveryFlag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		String sqlQuery1 = "UPDATE test.order SET DateOfOrder = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery1);
				ps.setString(1, delivery.getDate());
				ps.setString(2, delivery.getCustomerID());
				ps.setString(3, delivery.getOrderNumber());
				ps.executeUpdate();

				addOrderDateFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (addDeliveryFlag && addOrderDateFlag)
			return true;
		return false;
	}

	public static Boolean UpdateAveragePrepareTime(ArrayList<String> items) {

		String userID = items.get(0);
		String orderNumber = items.get(1);
		String averagePrepareTime = items.get(2);

		String selectQuery = "UPDATE test.order SET AveragePrepareTime = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(selectQuery);
				ps.setString(1, averagePrepareTime);
				ps.setString(2, userID);
				ps.setString(3, orderNumber);

				ps.executeUpdate();

				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static Boolean UpdateRefundDetails(ArrayList<String> items) {

		String UserID = items.get(0);
		String RestaurantName = items.get(1);
		String RefundAmount = items.get(2);
		String Key = UserID + "_" + RestaurantName;

		String selectQuery = "UPDATE test.refund SET RefundAmount=? WHERE CustomerId_RestaurantName = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(selectQuery);
				ps.setString(1, RefundAmount);
				ps.setString(2, Key);

				ps.executeUpdate();

				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static Boolean addRefund(ArrayList<String> items) {
		String customerID = items.get(0);
		String restaurantName = items.get(1);
		String refundAmount = items.get(2);
		String sqlQuery = "INSERT INTO test.refund values (?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, customerID + "_" + restaurantName);
				pst.setString(2, refundAmount);

				pst.executeUpdate();

				pst.close();
				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

}
