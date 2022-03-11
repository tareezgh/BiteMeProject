package logic;

import java.io.Serializable;

/**
 * represents a Log in the system
 */
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Log id is specific for each log */
	private String logID;
	/** Log type - Income, order or performance */
	private String logType;
	/** number of order we have */
	private String numberOfOrders;
	/** mount number of the log */
	private String monthNum;
	/** incomes for the restaurant in specific month */
	private String incomes;
	/** Logs for specific restaurant branch */
	private String restaurantBranch;
	/** Logs for specific restaurant */
	private String restaurantName;

	/**
	 * @param LogID            log id for each log
	 * @param LogType          Income, order or performance
	 * @param numberOfOrders   how many orders we have
	 * @param monthNum         specific month
	 * @param incomes          incomes calculate
	 * @param RestaurantBranch which branch
	 * @param RestaurantName   which restaurant
	 */
	public Log(String LogID, String LogType, String numberOfOrders, String monthNum, String incomes,
			String RestaurantBranch, String RestaurantName) {
		super();
		this.logID = LogID;
		this.logType = LogType;
		this.numberOfOrders = numberOfOrders;
		this.monthNum = monthNum;
		this.incomes = incomes;
		this.restaurantBranch = RestaurantBranch;
		this.restaurantName = RestaurantName;
	}

	public String getLogID() {
		return logID;
	}

	public void setLogID(String logID) {
		this.logID = logID;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(String numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public String getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(String monthNum) {
		this.monthNum = monthNum;
	}

	public String getIncomes() {
		return incomes;
	}

	public void setIncomes(String incomes) {
		this.incomes = incomes;
	}

	public String getRestaurantBranch() {
		return restaurantBranch;
	}

	public void setRestaurantBranch(String restaurantBranch) {
		this.restaurantBranch = restaurantBranch;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Log [LogID=" + logID + ", LogType=" + logType + ", numberOfOrders=" + numberOfOrders + ", monthNum="
				+ monthNum + ", incomes=" + incomes + ", RestaurantBranch=" + restaurantBranch + ", RestaurantName="
				+ restaurantName + "]";
	}

}
