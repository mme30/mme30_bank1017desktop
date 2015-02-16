package edu.pitt.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class SqLiteUtilities implements DbUtilities {

	@Override
	public ResultSet getResultSet(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean executeQuery(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DefaultTableModel getDataTable(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultTableModel getDataTable(String sqlQuery,
			String[] customColumnNames) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeDbConnection() {
		// TODO Auto-generated method stub
		
	}

}
