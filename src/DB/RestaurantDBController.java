package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * Class to handles all requests from client to access the DB, Here the requests
 * that relevant for Restaurant. Additional to our Package diagram. Requests
 * Requests identified by the name of the request
 *
 */
public class RestaurantDBController {
	static ResultSet rs;

	public static Boolean changeOrderStatus(ArrayList<String> items) { // items = customerID, orderNum,new status
		String customerID = items.get(0);
		String orderNumber = items.get(1);
		String newStatus = items.get(2);
		String sqlQuery = "UPDATE test.order SET status = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, customerID);
				ps.setString(3, orderNumber);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Boolean changeOrderApproveTime(ArrayList<String> items) { // items = customerID, orderNum, time
		String customerID = items.get(0);
		String orderNumber = items.get(1);
		String approveTimeByRestaurant = items.get(2);
		String sqlQuery = "UPDATE test.order SET ApproveTimeByRestaurant = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, approveTimeByRestaurant);
				ps.setString(2, customerID);
				ps.setString(3, orderNumber);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean addMealToMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String category = items.get(1);
		String mealName = items.get(2);
		String size = items.get(3);
		String priceBySize = items.get(4);

		boolean addMealToMenuFlag = false;
		boolean addMealToMealInRestaurantFlag = false;
		String sqlQuery = "INSERT INTO test.menu values (?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, restaurantName + "_" + mealName + "_" + size);
				pst.setString(2, mealName);
				pst.setString(3, category);
				pst.setString(4, priceBySize);
				pst.setString(5, size);

				pst.executeUpdate();

				pst.close();
				addMealToMenuFlag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		String sqlQuery1 = "INSERT INTO test.mealinrestaurant values (?,?,?,?);"; /// test.

		PreparedStatement pst1 = null;
		try {
			if (DBConnector.myConn != null) {
				pst1 = DBConnector.myConn.prepareStatement(sqlQuery1);

				pst1.setString(1, restaurantName + "_" + mealName + "_" + size);
				pst1.setString(2, restaurantName);
				pst1.setString(3, mealName);
				pst1.setString(4, category);

				pst1.executeUpdate();

				pst1.close();
				addMealToMealInRestaurantFlag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (addMealToMenuFlag && addMealToMealInRestaurantFlag)
			return true;
		return false;
	}

	public static Boolean UpdateMealsPriceInMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String mealName = items.get(1);
		String size = items.get(2);
		String newPrice = items.get(3);
		String Key = restaurantName + "_" + mealName + "_" + size;
		String sqlQuery = "UPDATE test.menu SET MealPrice = ? WHERE MealCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newPrice);
				ps.setString(2, Key);

				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean DeleteMealFromMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String mealName = items.get(1);
		String size = items.get(2);

		boolean deleteMealFromMenuFlag = false;
		boolean deleteMealFromMealInRestaurantFlag = false;

		String Key = restaurantName + "_" + mealName + "_" + size;
		String sqlQuery = "DELETE FROM test.menu WHERE MealCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, Key);

				ps.executeUpdate();
				deleteMealFromMenuFlag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sqlQuery1 = "DELETE FROM test.mealinrestaurant WHERE MenuCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps1 = DBConnector.myConn.prepareStatement(sqlQuery1);
				ps1.setString(1, Key);

				ps1.executeUpdate();
				deleteMealFromMealInRestaurantFlag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (deleteMealFromMenuFlag && deleteMealFromMealInRestaurantFlag)
			return true;
		return false;
	}

}
