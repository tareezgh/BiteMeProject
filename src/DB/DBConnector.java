package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import logic.Log;
import logic.Order;
import logic.Restaurant;



public class DBConnector {

	public static Connection myConn;

	public static Boolean connectToDB(String url, String username, String password) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {

			System.out.println("Driver definition failed: " + ex);
			return false;
		}
		try {
			myConn = DriverManager.getConnection(url, username, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					String restaurantName, restaurantBranch, mealPriceForLog;
					ArrayList<Log> logsToCollect = new ArrayList<Log>();
					ArrayList<Order> order = OrderDBController.getAllOrderDetails();
					ArrayList<Restaurant> res = OrderDBController.getAllRestaurantsFullName(); // getting all
																									// restaurants
																									// full names
					int totalPriceCalculate = 0;
					int countOrders = 0;
					int flag = 0;
					char[] dayNum = new char[2]; // for day number
					char[] prevMonth = new char[2]; // for month number
					char[] orderMonth = new char[2]; // for order months
					String currentDate, monthAfterCalculation;
					Log log = new Log(null, null, null, null, null, null, null);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDateTime now = LocalDateTime.now();
					currentDate = dtf.format(now);
					dayNum[0] = currentDate.charAt(3); // save day for check
					dayNum[1] = currentDate.charAt(4);
					if (String.valueOf(dayNum).equals("01")) // if its the first day for this month generate for last
																// month
					{

						prevMonth[0] = currentDate.charAt(0);
						prevMonth[1] = currentDate.charAt(1);

						if (String.valueOf(prevMonth).equals("01")) {
							prevMonth[0] = '1';
							prevMonth[1] = '2';
						} else if (String.valueOf(prevMonth).equals("02")) {
							prevMonth[0] = '0';
							prevMonth[1] = '1';
						} else if (String.valueOf(prevMonth).equals("03")) {
							prevMonth[0] = '0';
							prevMonth[1] = '2';
						} else if (String.valueOf(prevMonth).equals("04")) {
							prevMonth[0] = '0';
							prevMonth[1] = '3';
						} else if (String.valueOf(prevMonth).equals("05")) {
							prevMonth[0] = '0';
							prevMonth[1] = '4';
						} else if (String.valueOf(prevMonth).equals("06")) {
							prevMonth[0] = '0';
							prevMonth[1] = '5';
						} else if (String.valueOf(prevMonth).equals("07")) {
							prevMonth[0] = '0';
							prevMonth[1] = '6';
						} else if (String.valueOf(prevMonth).equals("08")) {
							prevMonth[0] = '0';
							prevMonth[1] = '7';
						} else if (String.valueOf(prevMonth).equals("09")) {
							prevMonth[0] = '0';
							prevMonth[1] = '8';
						} else if (String.valueOf(prevMonth).equals("10")) {
							prevMonth[0] = '0';
							prevMonth[1] = '9';
						} else if (String.valueOf(prevMonth).equals("11")) {
							prevMonth[0] = '1';
							prevMonth[1] = '0';
						} else if (String.valueOf(prevMonth).equals("12")) {
							prevMonth[0] = '1';
							prevMonth[1] = '1';
						}

						for (int j = 0; j < res.size(); j++) {
							for (int i = 0; i < order.size(); i++) {

								if (res.get(j).getRestaurantName().equals(order.get(i).getRestaurantName())) {
									String monthNumber = order.get(i).getDateOfOrder();
									orderMonth[0] = monthNumber.charAt(5);
									orderMonth[1] = monthNumber.charAt(6);
									monthNumber = String.valueOf(orderMonth);
									if (String.valueOf(prevMonth).equals(monthNumber)) {
										flag = 1;
										mealPriceForLog = order.get(i).getMealPrice();
										totalPriceCalculate = totalPriceCalculate + Integer.parseInt(mealPriceForLog);
										countOrders++;
									}
								}

							}
							if (flag == 1) {
								// start building the log
								log.setMonthNum(String.valueOf(prevMonth));
								log.setLogType("Income");
								log.setIncomes(String.valueOf(totalPriceCalculate));
								log.setRestaurantName(res.get(j).getRestaurantName());
								log.setRestaurantBranch(res.get(j).getRestaurantBranch());
								log.setNumberOfOrders(String.valueOf(countOrders));
								log.setLogID(String.valueOf(j));
								flag = 0;
								countOrders = 0;
								totalPriceCalculate = 0;

								logsToCollect.add(log);
								log = new Log(null, null, null, null, null, null, null);
							}

						}
					}
					BMManagerDBController.addLogs(logsToCollect);

					try {
						Thread.sleep(1000 * 60 * 60 * 24);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		t1.start();
		
		
		return true;

	}

	public void closeConnection() {
		try {
			if (!myConn.isClosed())
				myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}