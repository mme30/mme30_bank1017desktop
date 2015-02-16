package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Security {
	private String userID;
	private ArrayList<String> groupNames = new ArrayList<String>();
	public Customer validateLogin(String loginName, int pin){

		String sql = "SELECT * FROM customer WHERE loginName = '" + loginName + "' AND pin = '" + pin + "';";
		Customer cust = null;
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				cust = new Customer(rs.getString("customerID"));
			}

		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();
		return cust;

	}

	/**
	 * Gets all groups for the userID passed in and adds it to an arraylist of String
	 * @param userID
	 * @return
	 */
	public ArrayList<String> listUserGroups(String userID){
		String sql = "SELECT groupName FROM groups where userID = '" + userID + ";";
		DbUtilities db = new MySqlUtilities();
		ResultSet rs;
		try {
			rs = db.getResultSet(sql);
			while(rs.next()){
				groupNames.add(rs.getString("groupName"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // debug
            ErrorLogger.log(e.getMessage()); // Log error
            ErrorLogger.log(sql); // Log SELECT query
		}

		return null;
	}


}
