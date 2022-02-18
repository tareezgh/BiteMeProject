package logic;

import java.io.Serializable;

/**
 * represents a Order and Delivery in the system
 */
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Restaurant specific ID number */
	private String restaurantID;
	/** Restaurant Name */
	private String restaurantName;
	/** Restaurant branch */
	private String restaurantBranch;
	/** Employee Name for the restaurant */
	private String employeeName;
	/** Employee Code for the restaurant */
	private String employeeCode;
	/** Restaurant status - Active or Frozeen */
	private String restaurantStatus;
	/** userName for restaurant to login */
	private String userName;
	/** password for restaurant to login */
	private String password;
	/** Restaurant authorization for login the system */
	private String authorizations;
	/** Restaurant email address */
	private String email;
	/** Restaurant phone number */
	private String phone;

	/**
	 * Constructor for basic details
	 * 
	 * @param restaurantID     restaurant ID Number
	 * @param userName         restaurant userName
	 * @param password         restaurant password
	 * @param restaurantName   restaurant name
	 * @param restaurantBranch restaurant branch
	 * @param email            restaurant email address
	 * @param phone            restaurant phone number
	 * @param authorizations   restaurant authorization
	 */
	public Restaurant(String restaurantID, String userName, String password, String restaurantName,
			String restaurantBranch, String email, String phone, String authorizations) {
		super();
		this.restaurantID = restaurantID;
		this.userName = userName;
		this.password = password;
		this.restaurantName = restaurantName;
		this.restaurantBranch = restaurantBranch;
		this.email = email;
		this.phone = phone;
		this.authorizations = authorizations;
	}

	/**
	 * Constructor for name and branch of the restaurant
	 * 
	 * @param restaurantName
	 * @param restaurantBranch
	 */
	public Restaurant(String restaurantName, String restaurantBranch) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantBranch = restaurantBranch;
	}

	/**
	 * Constructor for all the details
	 * 
	 * @param restaurantID     restaurant ID Number
	 * @param restaurantName   restaurant name
	 * @param restaurantBranch restaurant branch
	 * @param employeeName     employee name of the restaurant
	 * @param employeeCode     employee code of the restaurant employer
	 * @param restaurantStatus restaurant status
	 * @param userName         restaurant userName
	 * @param password         restaurant password
	 * @param authorizations   restaurant authorization
	 * @param email            restaurant email address
	 * @param phone            restaurant phone number
	 */
	public Restaurant(String restaurantID, String restaurantName, String restaurantBranch, String employeeName,
			String employeeCode, String restaurantStatus, String userName, String password, String authorizations,
			String email, String phone) {
		super();
		this.restaurantID = restaurantID;
		this.restaurantName = restaurantName;
		this.restaurantBranch = restaurantBranch;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.restaurantStatus = restaurantStatus;
		this.userName = userName;
		this.password = password;
		this.authorizations = authorizations;
		this.email = email;
		this.phone = phone;
	}

	public String getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
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

	public String getRestaurantStatus() {
		return restaurantStatus;
	}

	public void setRestaurantStatus(String restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(String authorizations) {
		this.authorizations = authorizations;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantName=" + restaurantName + ", restaurantBranch=" + restaurantBranch
				+ ", employeeName=" + employeeName + ", employeeCode=" + employeeCode + ", restaurantStatus="
				+ restaurantStatus + ", userName=" + userName + ", password=" + password + ", authorizations="
				+ authorizations + ", email=" + email + ", phone=" + phone + "]";
	}

}
