package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Transaction {
	private String transactionID;
	private String accountID;
	private String type;
	private double amount;
	private double balance;
	private Date transactionDate; 
	/**
	 * Creates Transaction and sets values.
	 * @param transactionID
	 */
	public Transaction(String transactionID){
		String sql = "SELECT * FROM transactions "; 
		sql += "WHERE transactionID = '" + transactionID + "'";
		System.out.println(sql);
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.transactionID = rs.getString("transactionID");
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.amount = rs.getDouble("amount");
				this.balance = rs.getDouble("balance");
				this.transactionDate = new Date();
			}
		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();
	}
    /**
     * Creates Transaction and Inserts it into the db
     * @param accountID
     * @param type
     * @param amount
     * @param balance
     */
	public Transaction(String accountID, String type, double amount, double balance){
		this.transactionID = UUID.randomUUID().toString();
		this.type = type;
		this.amount = amount;
		this.accountID = accountID;
		this.balance = balance;

		String sql = "INSERT INTO transactions ";
		sql += "(transactionID, accountID, amount, transactionDate, type, balance) ";
		sql += " VALUES ";
		sql += "('" + this.transactionID + "', ";
		sql += "'" + this.accountID + "', ";
		sql += amount + ", ";
		sql += "CURDATE(), ";
		sql += "'" + this.type + "', ";
		sql += balance + ");";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


}
