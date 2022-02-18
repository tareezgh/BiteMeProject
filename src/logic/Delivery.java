package logic;

import java.io.Serializable;

/**
 * represents a Delivery in the system
 */
public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Delivery number for order */
	private String DeliveryNum;
	/** Customer id who is ordered */
	private String CustomerID;
	/** Order number */
	private String OrderNumber;
	/** Type of payment Business or Private */
	private String TypeOfPayment;
	/** Type of payment Regular, Shared or Robot */
	private String TypeOfDelivery;
	/** The time when the customer wants the order */
	private String Time;
	/** The date when the customer wants the order */
	private String Date;
	/** Customer name for delivery info */
	private String CustomerName;
	/** Customer phone for delivery info */
	private String PhoneNum;
	/** Customer address for delivery info */
	private String Address;
	/** Employee name for business account */
	private String EmployeeName;
	/** Employee code for business account */
	private String EmployeeCode;
	/** ShipmentTime of delivery */
	private String ShipmentTime;
	/** Order price */
	private String Price;
	/** Base price for delivery type */
	private String BasePrice;

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
		DeliveryNum = deliveryNum;
		CustomerID = customerID;
		OrderNumber = orderNumber;
		TypeOfPayment = typeOfPayment;
		TypeOfDelivery = typeOfDelivery;
		Time = time;
		Date = date;
		CustomerName = customerName;
		PhoneNum = phoneNum;
		Address = address;
		EmployeeName = employeeName;
		EmployeeCode = employeeCode;
		ShipmentTime = shipmentTime;
		Price = price;
		BasePrice = basePrice;
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
			String typeOfDelivery, String Time, String Date) {
		super();
		DeliveryNum = deliveryNum;
		CustomerID = customerID;
		OrderNumber = orderNumber;
		TypeOfPayment = typeOfPayment;
		TypeOfDelivery = typeOfDelivery;
		this.Time = Time;
		this.Date = Date;
	}

	public String getOrderNum() {
		return String.valueOf(orderNum);
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getDeliveryNum() {
		return DeliveryNum;
	}

	public void setDeliveryNum(String deliveryNum) {
		DeliveryNum = deliveryNum;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getTypeOfPayment() {
		return TypeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		TypeOfPayment = typeOfPayment;
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeCode() {
		return EmployeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		EmployeeCode = employeeCode;
	}

	public String getShipmentTime() {
		return ShipmentTime;
	}

	public void setShipmentTime(String shipmentTime) {
		ShipmentTime = shipmentTime;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getBasePrice() {
		return BasePrice;
	}

	public void setBasePrice(String basePrice) {
		BasePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Delivery [DeliveryNum=" + DeliveryNum + ", CustomerID=" + CustomerID + ", OrderNumber=" + OrderNumber
				+ ", TypeOfPayment=" + TypeOfPayment + ", TypeOfDelivery=" + TypeOfDelivery + ", Time=" + Time
				+ ", Date=" + Date + ", CustomerName=" + CustomerName + ", PhoneNum=" + PhoneNum + ", Address="
				+ Address + ", EmployeeName=" + EmployeeName + ", EmployeeCode=" + EmployeeCode + ", ShipmentTime="
				+ ShipmentTime + ", Price=" + Price + ", BasePrice=" + BasePrice + "]";
	}

}
