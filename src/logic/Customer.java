package logic;

import java.io.Serializable;

/**
 * represents a Customer in the system
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Customer id specific for customer */
	private String customerID;
	/** userName for customer to login */
	private String userName;
	/** password for customer to login */
	private String password;
	/** Customer first name */
	private String firstName;
	/** Customer last name */
	private String lastName;
	/** Customer home address */
	private String address;
	/** Customer email address */
	private String email;
	/** Customer phone number */
	private String phone;
	/** Customer authorization for login the system */
	private String authorization;
	/** Account type - Business or private */
	private String accountType;
	/** Status - Active or frozen */
	private String status;
	/** w4c specific for each customer allows to use the system */
	private String w4c;
	/** Employee name for Business customer */
	private String employeeName;
	/** Employee code for Business customer */
	private String employeeCode;
	/** Customer credit card number */
	private String creditCard;
	/** Business customer budget */
	private String billing;

	/**
	 * Constructor for basic details
	 * 
	 * @param customerID    customer ID Number
	 * @param userName      customer userName
	 * @param password      customer password
	 * @param firstName     customer first name
	 * @param lastName      customer last name
	 * @param address       customer address
	 * @param email         customer email
	 * @param phone         customer phone
	 * @param authorization customer authorization
	 */
	public Customer(String customerID, String userName, String password, String firstName, String lastName,
			String address, String email, String phone, String authorization) {
		super();
		this.customerID = customerID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.authorization = authorization;
	}

	/**
	 * Constructor for all the details
	 * 
	 * @param customerID    customer ID Number
	 * @param userName      customer userName
	 * @param password      customer password
	 * @param firstName     customer first name
	 * @param lastName      customer last name
	 * @param address       customer address
	 * @param email         customer email
	 * @param phone         customer phone
	 * @param authorization customer authorization
	 * @param accountType   account type - Business or Private
	 * @param status		status - Active or Frozeen
	 * @param w4c			w4c specific for each customer
	 * @param employeeName  employee name
	 * @param employeeCode  employee code
	 * @param creditCard    customer credit card
	 * @param billing       business customer budget
	 */
	public Customer(String customerID, String userName, String password, String firstName, String lastName,
			String address, String email, String phone, String authorization, String accountType, String status,
			String w4c, String employeeName, String employeeCode, String creditCard, String billing) {
		super();
		this.customerID = customerID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.authorization = authorization;
		this.accountType = accountType;
		this.status = status;
		this.w4c = w4c;
		this.employeeName = employeeName;
		this.employeeCode = employeeCode;
		this.creditCard = creditCard;
		this.billing = billing;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getW4C() {
		return w4c;
	}

	public void setW4C(String w4c) {
		this.w4c = w4c;
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

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", userName=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", authorization=" + authorization + ", accountType=" + accountType + ", status="
				+ status + ", w4c=" + w4c + ", employeeName=" + employeeName + ", employeeCode=" + employeeCode
				+ ", creditCard=" + creditCard + ", billing=" + billing + "]";
	}

}
