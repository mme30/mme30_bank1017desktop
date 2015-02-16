package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * This class is used to create instances of Customers.
 * @author Mohamed Eshnuk
 *
 */
public class Customer {
	private String customerID;
	private String firstName;
	private String lastName;
	private String ssn;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String loginName;
	private int pin;
	/**
	 * Retrieves a customer Object
	 * @param customerID
	 */
	public Customer(String customerID){
		 String sql = "SELECT * FROM customer Where customerID = '" + customerID + "';";
				 
		 DbUtilities db = new MySqlUtilities();
			try {
				ResultSet rs = db.getResultSet(sql);
				while(rs.next()){
					this.customerID = customerID;
					this.firstName = rs.getString("firstName");
					this.lastName = rs.getString("lastName");
					this.ssn = rs.getString("ssn");
					this.streetAddress = rs.getString("streetAddress");
					this.city = rs.getString("city");
					this.state = rs.getString("state");
					this.zip = rs.getString("zip");
					this.loginName = rs.getString("loginName");
					this.pin = rs.getInt("pin");
				}
				
			} catch (SQLException e) {
				e.printStackTrace(); // debug
	            ErrorLogger.log(e.getMessage()); // Log error
	            ErrorLogger.log(sql); // Log SELECT query
			}
			db.closeDbConnection();
		}
	/**
	 * This constructor Creates a Customer and inserts it into the db
	 * @param lastName
	 * @param firstName
	 * @param ssn
	 * @param loginName
	 * @param pin
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Customer(String lastName, String firstName, String ssn, String loginName, int pin, String streetAddress, String city, String state, int zip){
		
		this.customerID = UUID.randomUUID().toString();
		this.lastName = lastName;
		this.firstName = firstName;
		this.ssn = ssn;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.loginName = loginName;
		this.pin = pin;
		
		String sql = "INSERT INTO customer ";
		sql += "(customerID,lastName,firstName,ssn,streetAddress,city,state,zip,loginName,pin) ";
		sql += " VALUES ";
		sql += "('" + this.customerID + "', ";
		sql += "'" + this.lastName + "', ";
		sql += this.firstName + ", ";
		sql += this.ssn + ", ";
		sql += this.streetAddress + ", ";
		sql += this.city + ", ";
		sql += this.state + ", ";
		sql += this.zip + ", ";
		sql += this.loginName + ", ";
		sql += "'" + this.pin + "', ";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	
	public String getCustomerID(){
		return this.customerID;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
