package DB;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Customer;
import logic.Order;
import logic.Restaurant;

import logic.UserManagment;

/**
 *
 * Class to handles all requests from client to access the DB, Here the requests
 * that relevant for all Users requests and LOGIN specific. Additional to our
 * Package diagram. Requests Requests identified by the name of the request
 *
 */
public class UserDBController {

	private static ArrayList<UserManagment> allUsersList = new ArrayList<UserManagment>();

	private static ArrayList<String> usersConnected = new ArrayList<String>();

	static ResultSet rs;

	public static String tryToConnect(ArrayList<String> user) {
		String username = user.get(0);
		String password = user.get(1);
		String table = user.get(2); // user, restaurant or customer

		String checkAuthorization = null;
		String status = null;

		boolean checkFieldsNotEmpty;
		boolean checkIfUserExist;
		boolean checkValidInfo;
		boolean userConnected;

		String sqlQuery = "SELECT UserName,Password,Authorization FROM " + table + ";";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);

				checkFieldsNotEmpty = checkFieldsNotEmpty(username, password); // if empty
				if (checkFieldsNotEmpty) {
					checkIfUserExist = checkIfUserExist(username); // true if user exist

					if (checkIfUserExist) {
						checkValidInfo = checkValidInfo(username, password); // if password incorrect

						if (!checkValidInfo)
							status = "The password is incorrect";
						else { // user exist, valid info, just get authorization
								// check if the user connect or not
							userConnected = userConnected(username);
							if (userConnected) {
								status = "The user is already connected";
							} else {
								checkAuthorization = checkAuthorization(username);
								if (checkAuthorization.equals("ERROR-Authorization"))
									status = "ERROR-LOGIN";
								else {
									addUserToUsersConnectedArrayList(username);
									System.out.println("usersConnected: " + usersConnected);
									status = checkAuthorization;
								}
							}
						}
					} else
						status = "The user does not exist";
				} else
					status = "You must fill all the fields";

				System.out.println("status " + status);

			} else
				System.out.println("myConn is NULL !");
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	// ****** get data ******

	public static ArrayList<UserManagment> getDataFromOutside() {

		String selectQuery = "SELECT * FROM users;";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					UserManagment userDetails = new UserManagment(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10));

					allUsersList.add(userDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allUsersList;

	}

	public static ArrayList<String> getUserDetails(UserManagment user) {
		String username = user.getUserName();
		String password = user.getPassword();
		ArrayList<String> usersList = new ArrayList<>();

		String sqlQuery = "SELECT * FROM user WHERE UserName = '" + username + "' AND Password ='" + password + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					usersList.add(rs.getString("ID"));
					usersList.add(rs.getString("FirstName"));
					usersList.add(rs.getString("LastName"));
					usersList.add(rs.getString("Address"));
					usersList.add(rs.getString("Email"));
					usersList.add(rs.getString("Phone"));
					usersList.add(rs.getString("Authorization"));
					usersList.add(rs.getString("Status"));

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public static ArrayList<String> getRestaurantDetails(UserManagment user) {
		String username = user.getUserName();
		String password = user.getPassword();
		ArrayList<String> restaurantsList = new ArrayList<>();

		String sqlQuery = "SELECT * FROM restaurant WHERE UserName = '" + username + "' AND Password ='" + password
				+ "';";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					restaurantsList.add(rs.getString("RestaurantID"));
					restaurantsList.add(rs.getString("RestaurantName_Branch"));
					restaurantsList.add(rs.getString("EmployeeName"));
					restaurantsList.add(rs.getString("EmployeeCode"));
					restaurantsList.add(rs.getString("RestaurantStatus"));
					restaurantsList.add(rs.getString("Authorization"));
					restaurantsList.add(rs.getString("Email"));
					restaurantsList.add(rs.getString("Phone"));

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantsList;

	}

	public static ArrayList<String> getCustomerDetails(UserManagment user) {
		String username = user.getUserName();
		String password = user.getPassword();
		ArrayList<String> customersList = new ArrayList<>();

		String sqlQuery = "SELECT * FROM customers WHERE UserName = '" + username + "' AND Password ='" + password
				+ "';";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					customersList.add(rs.getString("customerID"));
					customersList.add(rs.getString("FirstName"));
					customersList.add(rs.getString("LastName"));
					customersList.add(rs.getString("Address"));
					customersList.add(rs.getString("Email"));
					customersList.add(rs.getString("Phone"));
					customersList.add(rs.getString("Authorization"));
					customersList.add(rs.getString("AccountType"));
					customersList.add(rs.getString("status"));
					customersList.add(rs.getString("W4C_card"));
					customersList.add(rs.getString("EmployeeName"));
					customersList.add(rs.getString("EmployeeCode"));
					customersList.add(rs.getString("CreditCard"));
					customersList.add(rs.getString("Billing"));

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customersList;

	}

	public static ArrayList<Customer> getCustomersDetailsAsUsers(ArrayList<Customer> messageData) {
		ArrayList<Customer> customersList = new ArrayList<>();
		String sqlQuery = "SELECT * FROM customers WHERE Authorization = 'User';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					Customer customerDetails = new Customer(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9));

					customersList.add(customerDetails);

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customersList;
	}

	public static ArrayList<Restaurant> getRestaurantDetailsAsUsers(ArrayList<Restaurant> messageData) {
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();
		String sqlQuery = "SELECT * FROM restaurant WHERE Authorization = 'User';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {

					String[] details = rs.getString(2).split("_");
					String restaurantName = details[0];
					String restaurantBranch = details[1];

					System.out.println("restaurantName: " + restaurantName);
					System.out.println("restaurantBranch: " + restaurantBranch);

					Restaurant restaurantDetails = new Restaurant(rs.getString(1), restaurantName, restaurantBranch,
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
							rs.getString(8), rs.getString(9), rs.getString(10));

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

	public static boolean usersCheckExist() {
		String selectQuery = "SELECT * FROM test.user;";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);

				if (rs.next() == false) {
					return false;
				}

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	// ****** Add data ******

	public static boolean addUsers(UserManagment user) {
		String sqlQuery = "INSERT INTO test.user values (?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, user.getiD());
				pst.setString(2, user.getUserName());
				pst.setString(3, user.getPassword());
				pst.setString(4, user.getFirstName());
				pst.setString(5, user.getLastName());
				pst.setString(6, user.getAddress());
				pst.setString(7, user.getEmail());
				pst.setString(8, user.getPhone());
				pst.setString(9, user.getAuthorization());
				pst.setString(10, user.getStatus());
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

	public static boolean addRestaurants(Restaurant restaurantList) {

		String sqlQuery = "INSERT INTO test.restaurant values (?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, restaurantList.getRestaurantID());
				pst.setString(2, restaurantList.getRestaurantName() + "_" + restaurantList.getRestaurantBranch()); // ******
				pst.setString(3, restaurantList.getEmployeeName());
				pst.setString(4, restaurantList.getEmployeeCode());
				pst.setString(5, restaurantList.getRestaurantStatus());
				pst.setString(6, restaurantList.getUserName());
				pst.setString(7, restaurantList.getPassword());
				pst.setString(8, restaurantList.getAuthorizations());
				pst.setString(9, restaurantList.getEmail());
				pst.setString(10, restaurantList.getPhone());
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

	public static boolean addCustomers(Customer customerList) {
		String sqlQuery = "INSERT INTO test.customers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, customerList.getCustomerID());
				pst.setString(2, customerList.getUserName());
				pst.setString(3, customerList.getPassword());
				pst.setString(4, customerList.getFirstName());
				pst.setString(5, customerList.getLastName());
				pst.setString(6, customerList.getAddress());
				pst.setString(7, customerList.getEmail());
				pst.setString(8, customerList.getPhone());
				pst.setString(9, customerList.getAuthorization());
				pst.setString(10, customerList.getAccountType());
				pst.setString(11, customerList.getStatus());
				pst.setString(12, customerList.getW4C());
				pst.setString(13, customerList.getEmployeeName());
				pst.setString(14, customerList.getEmployeeCode());
				pst.setString(15, customerList.getCreditCard());
				pst.setString(16, customerList.getBilling());
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

	// ****** Login methods ******

	private static boolean checkFieldsNotEmpty(String username, String password) {
		if (username.isEmpty() || password.isEmpty())
			return false;
		return true;
	}

	private static String checkAuthorization(String username) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				// search for the user in the query
				if (username.equals(rs.getString(1)))
					return rs.getString(3); // return Authorization
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ERROR-Authorization";
	}

	private static boolean checkIfUserExist(String username) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (username.equals(rs.getString(1))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean checkValidInfo(String username, String password) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean userConnected(String username) {
		return usersConnected.contains(username);
	}

	private static void addUserToUsersConnectedArrayList(String username) {
		usersConnected.add(username);
	}

	public static boolean removeUserFromLoginArr(String username) {
		usersConnected.remove(username);
		return true;
	}

	// ****** End login methods ******

}
