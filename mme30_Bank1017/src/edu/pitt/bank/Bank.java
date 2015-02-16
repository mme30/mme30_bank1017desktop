package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Bank {
	private ArrayList<Account> accountList = new ArrayList<Account>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();

	public Bank(){
		loadAccounts();
		setAccountOwners();
	}

	/**
	 * This method gets a list of all the accounts in the database and puts each one into an arrayList.
	 */
	public void loadAccounts(){
		String sql = "SELECT * FROM account "; 
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				Account acc = new Account(rs.getString("accountID"));
				accountList.add(acc);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();

	}
	/**
	 * Searches through all accounts and returns the account object that matches the accountID passed in.
	 * @param accountID
	 * @return
	 */
	public Account findAccount(String accountID){

		for (Account tempAcc : accountList){
			if (tempAcc.getAccountID() == accountID){ 
				Account account = new Account(accountID);
				return account;
			}
		}
		return null;
	}

	/**
	 * Searches through all Customers and returns the Customer object that matches the customerID passed in.
	 * @param customerID
	 * @return
	 */
	public Customer findCustomer(String customerID){

		for (Customer tempCust : customerList){
			if (tempCust.getCustomerID() == customerID){ 
				Customer customer = new Customer(customerID);
				return customer;
			}
		}
		return null;
	}
	/**
	 * Gets a list of all accounts and their corresponding customers. and adds them to Customerlist and accountList
	 */
	private void setAccountOwners(){
		String sql = "SELECT * FROM account "; 
		sql += " JOIN customer_account ON accountID = fk_accountID "; 
		sql += " JOIN customer ON fk_customerID = customerID "; 
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				Customer c = new Customer(rs.getString("customerID"));
				this.customerList.add(c);
				Account a = this.findAccount(rs.getString("accountID"));
				a.addAccountOwner(c);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();
	}


}
