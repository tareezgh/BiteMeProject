package logic;

import java.io.Serializable;

/**
 * represents a Client in the system
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public String hostName;
	public String clientIp;
	public String clientConnected;


	/**
	 * @param hostName         server IP Address
	 * @param clientIp		   client IP address
	 * @param clientConnected  status connection
	 */
	public Client(String hostName, String clientIp, String clientConnected) {
		super();
		this.hostName = hostName;
		this.clientIp = clientIp;
		this.clientConnected = clientConnected;
	}

	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


	public String getClientConnected() {
		return clientConnected;
	}


	public void setClientConnected(String clientConnected) {
		this.clientConnected = clientConnected;
	}


	@Override
	public String toString() {
		return "Client [hostName=" + hostName + ", clientIp=" + clientIp + ", clientConnected=" + clientConnected + "]";
	}

	

}
