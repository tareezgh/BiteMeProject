package logic;

import java.io.Serializable;

/**
 * represents a Employer in the system
 */
public class Employer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Specific code for business account */
	private String employerCode;
	/** Employee name for business account */
	private String employerName;
	/** Employee status - Approved or decline */
	private String employerStatus;
	
	/**
	 * @param employerCode Employee code for each employee
	 * @param employerName Employee name
	 */
	public Employer(String employerCode, String employerName) {
		this.employerCode = employerCode;
		this.employerName = employerName;
		
	}

	
	public void setEmployerCode(String employerCode) {
		this.employerCode = employerCode;
	}
	
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public String getEmployerCode() {
		return employerCode;
	}
	
	public String getEmployerName() {
		return employerName;
	}


	public String getEmployerStatus() {
		return employerStatus;
	}


	public void setEmployerStatus(String employerStatus) {
		this.employerStatus = employerStatus;
	}
}
