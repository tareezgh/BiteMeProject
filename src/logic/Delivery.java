package logic;

import java.io.Serializable;

/**
 * represents a Delivery in the system
 */
public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Delivery number for order */
	private String deliveryNum;
	/** Customer id who is ordered */
	private String customerID;
	/** Order number */
	private String orderNumber;
	/** Type of payment Business or Private */
	private String typeOfPayment;
	/** Type of payment Regular, Shared or Robot */
	private String typeOfDelivery;
	/** The time when the customer wants the order */
	private String time;
	/** The date when the customer wants the order */
	private String date;
	/** Customer name for delivery info */
	private String customerName;
	/** Customer phone for delivery info */
	private String phoneNum;
	/** Customer address for delivery info */
	private String address;
	/** Employee name for business account */
	private String employeeName;
	/** Employee code for business account */
	private String employeeCode;
	/** ShipmentTime of delivery */
	private String shipmentTime;
	/** Order price */
	private String price;
	/** Base price for delivery type */
	private String basePrice;

	private int orderNum = 0;

	/**
	 * Constructor for all delivery details
	 * 
	 * @param deliveryNum    Delivery number
	 * @param customerID     Customer ID
	 * @param orderNumber    Order number
	 * @param typeOfPayment  type of Payment - business or private
	 * @param typeOfDelivery type of delivery - Regular, Shared or Robot
	 * @param time           time of order
	 * @param date           date of order
	 * @param customerName   Customer name
	 * @param phoneNum       Customer phone
	 * @param address        Customer
	 * @param employeeName   Employee name
	 * @param employeeCode   Employee code
	 * @param shipmentTime   Shipment time
	 * @param price          Price
	 * @param basePrice      base price
	 */
	public Delivery(String deliveryNum, String customerID, String orderNumber, String typeOfPayment,
			String typeOfDelivery, String time, String date, String customerName, String phoneNum, String address,
			String employeeName, String employeeCode, String shipmentTime, String price, String basePrice) {
		super();
		this.deliveryNum = deliveryNum;
		this.customerID = customerID;
		this.orderNumber = orderNumber;
		this.typeOfPayment = typeOfPayment;
		this.typeOfDelivery = typeOfDelivery;
		this.time = time;
		this.date = date;
		this.customerName = customerName;
		this.phoneNum = phoneNum;
		this.address = address;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.shipmentTime = shipmentTime;
		this.price = price;
		this.basePrice = basePrice;
	}

	/**
	 * Constructor for basic delivery details
	 * 
	 * @param deliveryNum    Delivery number
	 * @param customerID     Customer ID
	 * @param orderNumber    Order number
	 * @param typeOfPayment  type of Payment - business or private
	 * @param typeOfDelivery type of delivery - Regular, Shared or Robot
	 * @param time           time of order
	 * @param date           date of order
	 */
	public Delivery(String deliveryNum, String customerID, String orderNumber, String typeOfPayment,
			String typeOfDelivery, String time, String date) {
		super();
		this.deliveryNum = deliveryNum;
		this.customerID = customerID;
		this.orderNumber = orderNumber;
		this.typeOfPayment = typeOfPayment;
		this.typeOfDelivery = typeOfDelivery;
		this.time = time;
		this.date = date;
	}

	public String getOrderNum() {
		return String.valueOf(orderNum);
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getDeliveryNum() {
		return deliveryNum;
	}

	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
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

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getShipmentTime() {
		return shipmentTime;
	}

	public void setShipmentTime(String shipmentTime) {
		this.shipmentTime = shipmentTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Delivery [DeliveryNum=" + deliveryNum + ", CustomerID=" + customerID + ", OrderNumber=" + orderNumber
				+ ", TypeOfPayment=" + typeOfPayment + ", TypeOfDelivery=" + typeOfDelivery + ", Time=" + time
				+ ", Date=" + date + ", CustomerName=" + customerName + ", PhoneNum=" + phoneNum + ", Address="
				+ address + ", EmployeeName=" + employeeName + ", EmployeeCode=" + employeeCode + ", ShipmentTime="
				+ shipmentTime + ", Price=" + price + ", BasePrice=" + basePrice + "]";
	}

}
