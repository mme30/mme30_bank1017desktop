package edu.pitt.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.pitt.bank.Account;
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.ErrorLogger;
import edu.pitt.utilities.MySqlUtilities;

import javax.swing.JButton;

public class TransactionUI {

	private JFrame frame;
	private JScrollPane transactionPane;
	private JTable tblTransactions;
	private Account account;

	/**
	 * Create the application.
	 */
	
	public TransactionUI(Account acc) {
		account = acc;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Account Transactions");
		frame.setBounds(100, 100, 482, 304);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		transactionPane = new JScrollPane();
		frame.getContentPane().add(transactionPane);
		DbUtilities db = new MySqlUtilities();
		String[] cols = {"Transaction ID", "Account Number", "Amount", "Transaction Date", "Type", "Balance"};
		String sql = "SELECT * FROM transactions;";
		try {
			DefaultTableModel transactionList = db.getDataTable(sql, cols);
			tblTransactions = new JTable(transactionList);
			tblTransactions.setFillsViewportHeight(true); // Tells the table to auto resize to the size of the parent containing it.
			tblTransactions.setShowGrid(true);
			tblTransactions.setGridColor(Color.black);
			transactionPane.setViewportView(tblTransactions);
		} catch (SQLException e) {
			e.printStackTrace(); // debug
            ErrorLogger.log(e.getMessage()); // Log error
            ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();
	}

}
