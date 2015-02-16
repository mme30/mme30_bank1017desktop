package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;
/**
 * This class is used to create instances of Accounts.
 * @author Mohamed Eshnuk
 *
 */
public class Account {
	private String accountID;
	private String type;
	private double balance;
	private double interestRate;
	private double penalty;
	private String status;
	private Date dateOpen;
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private ArrayList<Customer> accountOwners = new ArrayList<Customer>();

	/**
	 * Constructor finds the account linked to the provided accountID  *Creates a transaction for that acc?
	 * @param accountID
	 */
	public Account(String accountID){
		String sql = "SELECT * FROM account "; 
		sql += "WHERE accountID = '" + accountID + "'";
		DbUtilities db = new MySqlUtilities(); // Need to change all db to use the interface.
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.balance = rs.getDouble("balance");
				this.interestRate = rs.getDouble("interestRate");
				this.penalty = rs.getDouble("penalty");
				this.status = rs.getString("status");
				this.dateOpen = new Date();

				String sqlz = "SELECT * FROM transaction "; 
				sqlz += "WHERE accountID = '" + accountID + "'";
				while(rs.next()){
					Transaction t = new Transaction(this.accountID);
					t.setAccountID(rs.getString(this.accountID));
					t.setTransactionID(rs.getString("transactionID"));
					t.setAccountID(rs.getString("accountID"));;
					t.setType(rs.getString("type"));
					t.setAmount(rs.getDouble("amount"));
					t.setBalance(rs.getDouble("balance"));;
					this.createTransaction(t.getAccountID(), t.getType(), t.getAmount(), t.getBalance());

				}

			}
		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query

		}
		db.closeDbConnection();
	}

	/**
	 * This creates a new account and inserts it into the database.
	 * @param accountType
	 * @param initialBalance
	 */
	public Account(String accountType, double initialBalance){
		this.accountID = UUID.randomUUID().toString();
		this.type = accountType;
		this.balance = initialBalance;
		this.interestRate = 0;
		this.penalty = 0;
		this.status = "active";
		this.dateOpen = new Date();

		String sql = "INSERT INTO account ";
		sql += "(accountID,type,balance,interestRate,penalty,status,dateOpen) ";
		sql += " VALUES ";
		sql += "('" + this.accountID + "', ";
		sql += "'" + this.type + "', ";
		sql += this.balance + ", ";
		sql += this.interestRate + ", ";
		sql += this.penalty + ", ";
		sql += "'" + this.status + "', ";
		sql += "CURDATE());";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}

	/**
	 * Method used for withdrawing from  database.
	 * @param amount
	 */
	public void withdraw(double amount){
		this.balance -= amount;
		createTransaction(this.accountID, this.type, amount, this.balance);
		updateDatabaseAccountBalance();
	}

	/**
	 * adds the amount deposited to the balance in the database.
	 * @param amount
	 */
	public void deposit(double amount){
		this.balance += amount;
		createTransaction(this.accountID, this.type, amount, this.balance);
		updateDatabaseAccountBalance();
	}
	/**
	 * Updates balance in database
	 */
	private void updateDatabaseAccountBalance(){
		String sql = "UPDATE account SET balance = " + this.balance + " ";
		sql += "WHERE accountID = '" + this.accountID + "';";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	/**
	 * This creates a transaction for the provided ID
	 * @param transactionID
	 * @return
	 */
	private Transaction createTransaction(String transactionID){
		Transaction t = new Transaction(transactionID);
		transactionList.add(t);
		return t;
	}
	/**
	 * Creates transaction and adds it to transaction Arraylist
	 * @param accountID
	 * @param type
	 * @param amount
	 * @param balance
	 * @return
	 */
	private Transaction createTransaction(String accountID, String type, double amount, double balance){
		Transaction t = new Transaction(accountID, type, amount, balance);
		transactionList.add(t);
		return t;
	}

	public void addAccountOwner(Customer accountOwner){
		this.accountOwners.add(accountOwner);
	}

	public String getAccountID(){
		return this.accountID;
	}

	public double getBalance(){
		return this.balance;
	}



	@Override
	public String toString(){
		return this.accountID;

	}



	public String getType() {
		return type;
	}



	public double getInterestRate() {
		return interestRate;
	}



	public double getPenalty() {
		return penalty;
	}



	public String getStatus() {
		return status;
	}



	public Date getDateOpen() {
		return dateOpen;
	}



	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}



	public ArrayList<Customer> getAccountOwners() {
		return accountOwners;
	}
}
