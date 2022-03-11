package logic;

import java.io.Serializable;

/**
 * represents a Order and Delivery in the system
 */
public class OrderAndDelivery implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Customer id specific for customer */
	private String customerID;
	/** Specific order number */
	private String orderNumber;
	/** Status of the order */
	private String status;
	/** Restaurant name of this order */
	private String restaurantName;
	/** Date of the order  */
	private String dateOfOrder;
	/** Delivery type for the order */
	private String typeOfDelivery;
	/** The time of the order */
	private String time;

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
		
		this.orderNumber = orderNumber;
		this.status = status;
		this.restaurantName = restaurantName;
		this.dateOfOrder = dateOfOrder;
		this.typeOfDelivery = typeOfDelivery;
		this.time = time;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getTypeOfDelivery() {
		return typeOfDelivery;
	}

	public void setTypeOfDelivery(String typeOfDelivery) {
		this.typeOfDelivery = typeOfDelivery;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "OrderAndDelivery [customerID=" + customerID + ", OrderNumber=" + orderNumber + ", status=" + status
				+ ", RestaurantName=" + restaurantName + ", DateOfOrder=" + dateOfOrder + ", TypeOfDelivery="
				+ typeOfDelivery + ", Time=" + time + "]";
	}

}
