package edu.pitt.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.pitt.bank.Customer;
import edu.pitt.bank.Security;
import edu.pitt.utilities.ErrorLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI {

	private JFrame frmBankLogin;
	private JTextField txtLogin;
	private JLabel lblLoginName;
	private JLabel lblPassword;
	private JTextField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frmBankLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); // debug
					ErrorLogger.log(e.getMessage()); // Log error

				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankLogin = new JFrame();
		frmBankLogin.setTitle("Bank1017 Login");
		frmBankLogin.setBounds(100, 100, 450, 300);
		frmBankLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBankLogin.getContentPane().setLayout(null);

		lblLoginName = new JLabel("Login Name:");
		lblLoginName.setBounds(25, 30, 73, 14);
		frmBankLogin.getContentPane().add(lblLoginName);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(25, 62, 73, 14);
		frmBankLogin.getContentPane().add(lblPassword);

		txtLogin = new JTextField();
		txtLogin.setBounds(141, 27, 203, 20);
		frmBankLogin.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(141, 59, 203, 20);
		frmBankLogin.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnExitLoginPage = new JButton("Exit");
		btnExitLoginPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExitLoginPage.setBounds(281, 112, 57, 23);
		frmBankLogin.getContentPane().add(btnExitLoginPage);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String loginName = txtLogin.getText();
					//String pin = txtPassword.getText();
					int pin = Integer.parseInt(txtPassword.getText());   //  16:00 vid, I have to handle possible erorrs here.
					Security s = new Security();
					Customer c = s.validateLogin(loginName, pin);
					if (c != null){
						AccountDetailsUI ad = new AccountDetailsUI(c);
						frmBankLogin.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Login");
					}
				}
				catch(NumberFormatException  ex){
					ErrorLogger.log("Invalid pin was entered");
					ErrorLogger.log(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Invalid pin");
				}
			}
		});
		btnLogin.setBounds(198, 112, 73, 23);
		frmBankLogin.getContentPane().add(btnLogin);
	}
}
