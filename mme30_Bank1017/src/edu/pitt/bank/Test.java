package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Test {

	public static void main(String[] args) {
		
		//Bank b = new Bank(); this caused an error
		
		// Once you create event handler for LoginUI use the 3 lines of code below as shown in vid 13:30
		/*
		Security s = new Security();
		Customer c = s.validateLogin("nmarcus", 8125);
		System.out.println(c.getLastName());
		*/
		//String accountID, String type, double amount, double balance)
		
		//Account a = new Account();
		//Transaction t = new Transaction("00ae9c2a-5d43-11e3-94ef-97beef767f1d", "ggg", 22, 2121);
		
		//public Transaction(String accountID, String type, double amount, double balance)
		
		
		/*
		DbUtilities db = new DbUtilities();
		String sql = "SELECT * FROM account;";
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				System.out.println(rs.getString("accountID"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorLogger.log(e.getMessage());
			
			
			
			
			String input =JOptionPane.showInputDialog("enter a number");
			
			try{
				int i = Integer.parseInt(input);
			}
			catch(NumberFormatException d){
			JOptionPane.showMessageDialog(null, "u must put a number");
			ErrorLogger.log("Invalid numba");
			ErrorLogger.log(d.getMessage());
			}
			
			
				
		}	
		*/			
		

	}

}
