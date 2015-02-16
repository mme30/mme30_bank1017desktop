package edu.pitt.ui;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import edu.pitt.bank.Account;
import edu.pitt.bank.Customer;
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.ErrorLogger;
import edu.pitt.utilities.MySqlUtilities;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AccountDetailsUI { 

	private JFrame frame;
	JComboBox cboAccounts;
	private JTextField textFieldAmount;
	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnExitAccDetails;
	private JButton btnShowTransactions;
	private JLabel lblNewLabel;
	private JLabel lblAmount;
	private JLabel lblAccountType;
	private JLabel lblBalance;
	private JLabel lblInterestRate;
	private JLabel lblPenalty;
	private Customer accountOwner;
	private Account acct;

	/**
	 * Create the application.
	 */
	public AccountDetailsUI(Customer c) {
		accountOwner = c;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.getContentPane().setLayout(null);
		cboAccounts = new JComboBox();
		cboAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account selectedAccount = (Account) cboAccounts.getSelectedItem();
				lblAccountType.setText("Account type: " + selectedAccount.getType());
				lblBalance.setText("Balance: " + selectedAccount.getBalance());
				lblInterestRate.setText("Interest Rate: " + selectedAccount.getInterestRate());
				lblPenalty.setText("Penalty: " + selectedAccount.getPenalty());
			}
		});
		cboAccounts.setBounds(107, 80, 274, 27);
		frame.getContentPane().add(cboAccounts);

		JLabel labelAccounts = new JLabel("Your accounts:");
		labelAccounts.setBounds(10, 82, 87, 22);
		frame.getContentPane().add(labelAccounts);

		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(263, 143, 118, 27);
		frame.getContentPane().add(textFieldAmount);
		textFieldAmount.setColumns(10);

		btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account selectedAccount = (Account) cboAccounts.getSelectedItem();
				selectedAccount.deposit(Double.parseDouble(textFieldAmount.getText()));
				lblAccountType.setText("Account type: " + selectedAccount.getType());
				lblBalance.setText("Balance: " + selectedAccount.getBalance());
				lblInterestRate.setText("Interest Rate: " + selectedAccount.getInterestRate());
				lblPenalty.setText("Penalty: " + selectedAccount.getPenalty());
			}
		});
		btnDeposit.setBounds(193, 181, 89, 23);
		frame.getContentPane().add(btnDeposit);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account selectedAccount = (Account) cboAccounts.getSelectedItem();
				selectedAccount.withdraw(Double.parseDouble(textFieldAmount.getText()));
				lblAccountType.setText("Account type: " + selectedAccount.getType());
				lblBalance.setText("Balance: " + selectedAccount.getBalance());
				lblInterestRate.setText("Interest Rate: " + selectedAccount.getInterestRate());
				lblPenalty.setText("Penalty: " + selectedAccount.getPenalty());
			}
		});
		btnWithdraw.setBounds(292, 181, 89, 23);
		frame.getContentPane().add(btnWithdraw);

		btnExitAccDetails = new JButton("Exit");
		btnExitAccDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitAccDetails.setBounds(313, 227, 68, 23);
		frame.getContentPane().add(btnExitAccDetails);

		btnShowTransactions = new JButton("Show Transactions");
		btnShowTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransactionUI t = new TransactionUI(acct);

			}
		});
		btnShowTransactions.setBounds(171, 227, 132, 23);
		frame.getContentPane().add(btnShowTransactions);
		lblNewLabel = new JLabel(accountOwner.getFirstName() + " " + accountOwner.getLastName() + ", " + "welcome to 1017 bank.");
		lblNewLabel.setBounds(10, 11, 371, 75);
		frame.getContentPane().add(lblNewLabel);

		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(207, 149, 57, 14);
		frame.getContentPane().add(lblAmount);

		lblAccountType = new JLabel("Account type:");
		lblAccountType.setBounds(10, 126, 161, 22);
		frame.getContentPane().add(lblAccountType);

		lblBalance = new JLabel("Balance: ");
		lblBalance.setBounds(10, 145, 161, 22);
		frame.getContentPane().add(lblBalance);

		lblInterestRate = new JLabel("Interest Rate:");
		lblInterestRate.setBounds(10, 167, 161, 14);
		frame.getContentPane().add(lblInterestRate);

		lblPenalty = new JLabel("Penalty:");
		lblPenalty.setBounds(10, 185, 161, 14);
		frame.getContentPane().add(lblPenalty);

		DbUtilities db = new MySqlUtilities();
		String sql = "SELECT accountID FROM account;";
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				acct = new Account(rs.getString("accountID"));
				cboAccounts.addItem(acct);	

			}

		} catch (SQLException e) {
			e.printStackTrace(); // debug
			ErrorLogger.log(e.getMessage()); // Log error
			ErrorLogger.log(sql); // Log SELECT query
		}
		db.closeDbConnection();


	}
}
