package logic;

import java.io.Serializable;

/**
 * represents a Order in the system
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Order number for specific order */
	private String orderNumber;
	/** Customer id which is ordered */
	private String customerID;
	/** meal name that have order */
	private String meal;
	/** category of the meal */
	private String category;
	/** size of the meal - Big, Medium and small */
	private String size;
	/** level of cook for Main Course category */
	private String lvlOfCook;
	/** restrictions choose by the customer */
	private String restrictions;
	/** Status of the order - Hold, Approve, Ready of Late */
	private String status;
	/** meal price by the size */
	private String mealPrice;
	/** Primary key to distinguish between orders */
	private String primaryKey;
	/** Quantity of the meal */
	private String quantity;
	/** Order from restaurant name */
	private String restaurantName;
	/** Order from restaurant branch */
	private String restaurantBranch;
	/** Date of order entered by the customer */
	private String dateOfOrder;
	/** Preparing time of the order to calculate the Average time for logs */
	private String averagePrepareTime;
	/**
	 * Approve time to calculate how much time it takes when the restaurant approve
	 */
	private String approveTimeByRestaurant;
	/** number of meals in one order */
	private int mealNum = 0;

	/**
	 * Constructor for basic order details
	 * 
	 * @param orderNumber        specific order number
	 * @param customerID         customer ID number
	 * @param meal               meal name
	 * @param category           meal category
	 * @param size               meal size
	 * @param lvlOfCook          level of cook of the meal
	 * @param restrictions       restriction of the meal
	 * @param status             status of the order
	 * @param averagePrepareTime preparing time
	 */
	public Order(String orderNumber, String customerID, String meal, String category, String size, String lvlOfCook,
			String restrictions, String status, String averagePrepareTime) {
		super();
		this.orderNumber = orderNumber;
		this.customerID = customerID;
		this.meal = meal;
		this.category = category;
		this.size = size;
		this.lvlOfCook = lvlOfCook;
		this.restrictions = restrictions;
		this.status = status;
		this.averagePrepareTime = averagePrepareTime;
	}

	/**
	 * Constructor for all order details
	 * 
	 * @param primaryKey              primary key for DB
	 * @param orderNumber             specific order number
	 * @param customerID              customer ID number
	 * @param meal                    meal name
	 * @param category                meal category
	 * @param size                    meal size
	 * @param lvlOfCook               level of cook of the meal
	 * @param restrictions            restriction of the meal
	 * @param status                  status of the order
	 * @param mealPrice               meal price by size
	 * @param restaurantName          restaurant name
	 * @param restaurantBranch        restaurant branch
	 * @param dateOfOrder             date of order inserted with delivery details
	 * @param averagePrepareTime      preparing time of the order
	 * @param approveTimeByRestaurant approve time or order
	 */
	public Order(String primaryKey, String orderNumber, String customerID, String meal, String category, String size,
			String lvlOfCook, String restrictions, String status, String mealPrice, String restaurantName,
			String restaurantBranch, String dateOfOrder, String averagePrepareTime, String approveTimeByRestaurant) {
		this.orderNumber = orderNumber;
		this.customerID = customerID;
		this.meal = meal;
		this.category = category;
		this.size = size;
		this.lvlOfCook = lvlOfCook;
		this.restrictions = restrictions;
		this.status = status;
		this.mealPrice = mealPrice;
		this.primaryKey = primaryKey;
		this.restaurantName = restaurantName;
		this.restaurantBranch = restaurantBranch;
		this.dateOfOrder = dateOfOrder;
		this.averagePrepareTime = averagePrepareTime;
		this.approveTimeByRestaurant = approveTimeByRestaurant;
	}

	public String getMealNum() {
		return String.valueOf(mealNum);
	}

	public void setMealNum(int mealNum) {
		this.mealNum = mealNum;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLvlOfCook() {
		return lvlOfCook;
	}

	public void setLvlOfCook(String lvlOfCook) {
		this.lvlOfCook = lvlOfCook;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(String mealPrice) {
		this.mealPrice = mealPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantBranch() {
		return restaurantBranch;
	}

	public void setRestaurantBranch(String restaurantBranch) {
		this.restaurantBranch = restaurantBranch;
	}

	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getAveragePrepareTime() {
		return averagePrepareTime;
	}

	public void setAveragePrepareTime(String averagePrepareTime) {
		this.averagePrepareTime = averagePrepareTime;
	}

	public String getApproveTimeByRestaurant() {
		return approveTimeByRestaurant;
	}

	public void setApproveTimeByRestaurant(String approveTimeByRestaurant) {
		this.approveTimeByRestaurant = approveTimeByRestaurant;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerID=" + customerID + ", meal=" + meal + ", category="
				+ category + ", size=" + size + ", lvlOfCook=" + lvlOfCook + ", restrictions=" + restrictions
				+ ", status=" + status + ", mealPrice=" + mealPrice + ", primaryKey=" + primaryKey + ", quantity="
				+ quantity + ", restaurantName=" + restaurantName + ", restaurantBranch=" + restaurantBranch
				+ ", dateOfOrder=" + dateOfOrder + ", averagePrepareTime=" + averagePrepareTime
				+ ", approveTimeByRestaurant=" + approveTimeByRestaurant + ", mealNum=" + mealNum + "]";
	}

}
