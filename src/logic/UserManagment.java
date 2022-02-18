package logic;

import java.io.Serializable;

/**
 * represents a User in the system - Users BMManager, CEO, HR, Restaurant,
 * Customer
 */
public class UserManagment implements Serializable {

	private static final long serialVersionUID = 1L;

	/** specific User ID number */
	private String iD;
	/** userName to login */
	private String userName;
	/** password to login */
	private String password;
	/** User first name */
	private String firstName;
	/** User last name */
	private String lastName;
	/** User address */
	private String address;
	/** User email */
	private String email;
	/** User phone */
	private String phone;
	/** User authorization */
	private String authorization;
	/** User status - Active or Frozen */
	private String status;

	/**
	 * Constructor for login details
	 * 
	 * @param username
	 * @param password
	 */
	public UserManagment(String username, String password) {
		this.userName = username;
		this.password = password;

	}

	/**
	 * @param iD            user ID number
	 * @param userName      user userName
	 * @param password      user password
	 * @param firstName     user first name
	 * @param lastName      user last name
	 * @param address       user address
	 * @param email         user email
	 * @param phone         user phone
	 * @param authorization user authorization
	 * @param status        user status
	 */
	public UserManagment(String iD, String userName, String password, String firstName, String lastName, String address,
			String email, String phone, String authorization, String status) {
		super();
		this.iD = iD;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.authorization = authorization;
		this.status = status;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserManagment [iD=" + iD + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address + ", email=" + email + ", phone="
				+ phone + ", authorization=" + authorization + ", status=" + status + "]";
	}

}
