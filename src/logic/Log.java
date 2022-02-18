package logic;

import java.io.Serializable;
import java.util.HashMap;

/**
 * represents a Log in the system
 */
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Log id is specific for each log */
	private String LogID;
	/** Log type - Income, order or performance */
	private String LogType;
	/** number of order we have */
	private String numberOfOrders;
	/** mount number of the log */
	private String monthNum;
	/** incomes for the restaurant in specific month */
	private String incomes;
	/** Logs for specific restaurant branch */
	private String RestaurantBranch;
	/** Logs for specific restaurant */
	private String RestaurantName;

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
		this.LogID = LogID;
		this.LogType = LogType;
		this.numberOfOrders = numberOfOrders;
		this.monthNum = monthNum;
		this.incomes = incomes;
		this.RestaurantBranch = RestaurantBranch;
		this.RestaurantName = RestaurantName;
	}

	public String getLogID() {
		return LogID;
	}

	public void setLogID(String logID) {
		LogID = logID;
	}

	public String getLogType() {
		return LogType;
	}

	public void setLogType(String logType) {
		LogType = logType;
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
		return RestaurantBranch;
	}

	public void setRestaurantBranch(String restaurantBranch) {
		RestaurantBranch = restaurantBranch;
	}

	public String getRestaurantName() {
		return RestaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		RestaurantName = restaurantName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Log [LogID=" + LogID + ", LogType=" + LogType + ", numberOfOrders=" + numberOfOrders + ", monthNum="
				+ monthNum + ", incomes=" + incomes + ", RestaurantBranch=" + RestaurantBranch + ", RestaurantName="
				+ RestaurantName + "]";
	}

}
