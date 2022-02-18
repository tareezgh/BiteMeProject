package logic;

import java.io.Serializable;
import java.util.HashMap;

/**
 * represents a Order and Delivery in the system
 */
public class OrderAndDelivery implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Customer id specific for customer */
	private String customerID;
	/** Specific order number */
	private String OrderNumber;
	/** Status of the order */
	private String status;
	/** Restaurant name of this order */
	private String RestaurantName;
	/** Date of the order  */
	private String DateOfOrder;
	/** Delivery type for the order */
	private String TypeOfDelivery;
	/** The time of the order */
	private String Time;

	/**
	 * @param orderNumber     order number
	 * @param status          the status of the order right now
	 * @param restaurantName  the restaurant where the customer ordered
	 * @param dateOfOrder     the date of order - when the customer requested
	 * @param typeOfDelivery  type of delivery - what the customer choose
	 * @param time		      the time of order - when the customer requested
	 */
	public OrderAndDelivery(String orderNumber, String status, String restaurantName,
			String dateOfOrder, String typeOfDelivery, String time) {
		super();
		
		OrderNumber = orderNumber;
		this.status = status;
		RestaurantName = restaurantName;
		DateOfOrder = dateOfOrder;
		TypeOfDelivery = typeOfDelivery;
		Time = time;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRestaurantName() {
		return RestaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		RestaurantName = restaurantName;
	}

	public String getDateOfOrder() {
		return DateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		DateOfOrder = dateOfOrder;
	}

	public String getTypeOfDelivery() {
		return TypeOfDelivery;
	}

	public void setTypeOfDelivery(String typeOfDelivery) {
		TypeOfDelivery = typeOfDelivery;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	@Override
	public String toString() {
		return "OrderAndDelivery [customerID=" + customerID + ", OrderNumber=" + OrderNumber + ", status=" + status
				+ ", RestaurantName=" + RestaurantName + ", DateOfOrder=" + DateOfOrder + ", TypeOfDelivery="
				+ TypeOfDelivery + ", Time=" + Time + "]";
	}

}
