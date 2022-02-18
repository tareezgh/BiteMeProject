package DB;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import common.MyFile;
import logic.Customer;
import logic.Employer;
import logic.FileData;
import logic.Log;
import logic.Restaurant;

/**
 *
 * Class to handles all requests from client to access the DB,Here the requests
 * that relevant for BMManager, HR and CEO. 
 * Additional to our Package diagram. Requests
 * Requests identified by the name of the request
 *
 */
public class BMManagerDBController {
	static ResultSet rs;

	// ****** Get data ******

	// BM
	public static ArrayList<Customer> getAllCustomerDetails(String Authorization) { // Authorization=Customer
		ArrayList<Customer> customersList = new ArrayList<>();

		String selectQuery = "SELECT * FROM customers WHERE Authorization = '" + Authorization + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Customer customerDetails = new Customer(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
							rs.getString(14), rs.getString(15), rs.getString(16));

					customersList.add(customerDetails);
				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customersList;

	}

	public static ArrayList<Restaurant> getAllRestaurantsDetails(String Authorization) { // Authorization=Restaurant
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();

		String selectQuery = "SELECT * FROM restaurant WHERE Authorization = '" + Authorization + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					String[] details = rs.getString(2).split("_");
					String restaurantName = details[0];
					String restaurantBranch = details[1];

					Restaurant restaurantDetails = new Restaurant(rs.getString(1), restaurantName, restaurantBranch,
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
							rs.getString(8), rs.getString(9), rs.getString(10));

					restaurantsList.add(restaurantDetails);
				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantsList;

	}

	// BM
	public static ArrayList<Employer> getEmployeeDetails(String status) {
		ArrayList<Employer> employerList = new ArrayList<>();

		String selectQuery = "SELECT * FROM employer WHERE EmployerStatus = '" + status + "';"; // 'Requested'
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Employer employee = new Employer(rs.getString(1), rs.getString(2));
					employee.setEmployerStatus(rs.getString(3));
					employerList.add(employee);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employerList;
	}

	// HR
	public static ArrayList<Customer> getAllRequstionForBuisnesAccount() {
		ArrayList<Customer> buisnessCustomerList = new ArrayList<>();

		String sqlQuery = "SELECT * FROM customers WHERE AccountType = 'Requested';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
							rs.getString(15), rs.getString(16));
					buisnessCustomerList.add(customer);
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buisnessCustomerList;
	}

	// ****** Change data ******

	// BM
	public static Boolean changeCustomerStatus(ArrayList<String> items) { // items = customerID, new status
		String customerID = items.get(0);
		String newStatus = items.get(1);
		String sqlQuery = "UPDATE customers SET status = ? WHERE customerID = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, customerID);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Boolean changeRestaurantStatus(ArrayList<String> items) {
		String restaurantID = items.get(0);
		String newStatus = items.get(1);
		String sqlQuery = "UPDATE restaurant SET RestaurantStatus = ? WHERE RestaurantID = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, restaurantID);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// BM
	public static Boolean changeEmployerStatus(ArrayList<String> items) { // items = EmployerCode, new status
		String EmployerCode = items.get(0);
		String newStatus = items.get(1);

		String sqlQuery = "UPDATE test.employer SET EmployerStatus = ? WHERE EmployerCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, EmployerCode);
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// HR
	public static boolean confirmBusinessAccount(ArrayList<String> items) {

		String id = items.get(0);
		String status = items.get(1);
		String biliing = items.get(2);
		String newStatus = null;
		String accountType = null;
		String w4c = id + id;
		String sqlQuery = "UPDATE customers SET AccountType = ?, status = ?, W4C_card= ? , Billing = ? WHERE customerID = ?;";

		switch (status) {
		case "Approve":
			accountType = "Business";
			newStatus = "Active";
			break;
		case "Decline":
			accountType = "Decline";
			newStatus = "Frozeen";
			break;
		}
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, accountType);
				ps.setString(2, newStatus);
				ps.setString(3, w4c);
				ps.setString(4, biliing);

				ps.setString(5, id);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// BM
	public static boolean updatePrivateAccount(ArrayList<String> items) {

		String iD = items.get(0);
		String creditCard = items.get(1);

		String sqlQuery = "UPDATE customers SET Authorization = 'Customer', AccountType = 'Private', status = 'Active', W4C_card = ?, CreditCard =? WHERE customerID = ?;";

		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, iD + iD);
				ps.setString(2, creditCard);
				ps.setString(3, iD);

				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Boolean changeCustomerAuthorization(ArrayList<String> items) {
		String userID = items.get(0);
		String creditCard = items.get(1);
		String accountType = items.get(2);
		String employeeName = items.get(3);
		String employeeCode = items.get(4);

		String selectQuery = "UPDATE customers SET Authorization = ?, AccountType = ?, status = ?, EmployeeName = ?, EmployeeCode = ?, CreditCard =?  WHERE customerID = ? ;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(selectQuery);
				System.out.println("inside DB ");
				ps.setString(1, "Customer");
				ps.setString(2, accountType);
				if (accountType.equals("Requested"))
					ps.setString(3, "Frozeen");
				else
					ps.setString(3, "Active");
				ps.setString(4, employeeName);
				ps.setString(5, employeeCode);
				ps.setString(6, creditCard);
				ps.setString(7, userID);

				ps.executeUpdate();

				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static Boolean changeRestaurantAuthorization(ArrayList<String> items) {
		String userID = items.get(0);
		String employeeName = items.get(1);
		String employeeCode = items.get(2);

		String selectQuery = "UPDATE restaurant SET Authorization=?, RestaurantStatus = ?, EmployeeName = ?, EmployeeCode = ? WHERE RestaurantID = ? ;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(selectQuery);
				ps.setString(1, "Restaurant");
				ps.setString(2, "Active");
				ps.setString(3, employeeName);
				ps.setString(4, employeeCode);
				ps.setString(5, userID);

				ps.executeUpdate();

				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// HR
	public static boolean insertNewEmployer(String employerCode, String employerName) {

		String sqlQuery = "INSERT INTO employer values (?,?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, employerCode);
				pst.setString(2, employerName);
				pst.setString(3, "Requested");

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

	public static Boolean addLogs(ArrayList<Log> messageData) {
		int maxIndex = 0;
		String LogID;
		String LogType;
		String numberOfOrders;
		String monthNum;
		String incomes;
		String RestaurantBranch;
		String RestaurantName;
		String index;
		String sqlQuery = "INSERT INTO test.logs values (?,?,?,?,?,?,?);";
		String selectQuery = "SELECT LogID FROM test.logs;";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					index = rs.getString(1);
					if (maxIndex < Integer.valueOf(index))
						maxIndex = Integer.valueOf(index);
				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				for (int i = 0; i < messageData.size(); i++) {
					maxIndex++;
					pst = DBConnector.myConn.prepareStatement(sqlQuery);
					LogID = String.valueOf(maxIndex);
					LogType = messageData.get(i).getLogType();
					numberOfOrders = messageData.get(i).getNumberOfOrders();
					monthNum = messageData.get(i).getMonthNum();
					incomes = messageData.get(i).getIncomes();
					RestaurantBranch = messageData.get(i).getRestaurantBranch();
					RestaurantName = messageData.get(i).getRestaurantName();

					pst.setString(1, LogID);
					pst.setString(2, LogType);
					pst.setString(3, numberOfOrders);
					pst.setString(4, monthNum);
					pst.setString(5, incomes);
					pst.setString(6, RestaurantBranch);
					pst.setString(7, RestaurantName);
					pst.executeUpdate();

					pst.close();
				}
				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;

	}

	public static ArrayList<Log> getLogs() {
		ArrayList<Log> logList = new ArrayList<>();
		System.out.println("getLogs NOW");
		String selectQuery = "SELECT * FROM test.logs;";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Log logDetails = new Log(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7));

					logList.add(logDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return logList;

	}

	public static ArrayList<FileData> getFiles() {
		ArrayList<FileData> fileList = new ArrayList<>();
		String selectQuery = "SELECT * FROM test.logarchive;";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Blob blob = rs.getBlob(3);
					int blobLength = (int) blob.length();
					byte[] blobAsBytes = blob.getBytes(1, blobLength);
				
					FileData fileDetails = new FileData(rs.getString(1), rs.getInt(2), blobAsBytes);

					fileList.add(fileDetails);
					

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fileList;

	}

	public static Boolean updateFile(MyFile saveFile) throws SerialException, SQLException {

		String sqlQuery = "INSERT INTO test.logarchive values (?,?,?);";
		PreparedStatement pst = null;
		InputStream targetStream = new ByteArrayInputStream(saveFile.getMybytearray());

		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, saveFile.getFileName());
				pst.setInt(2, saveFile.getSize());
				pst.setBlob(3, targetStream);

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
