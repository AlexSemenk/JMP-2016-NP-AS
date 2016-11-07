package com.epam.jmp.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSource {

	private static final String URL = "jdbc:mysql://localhost:3306/user";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "pass";

	public Connection getConnection() throws UserDaoException {
		Properties info = new Properties();
		info.setProperty("user", USERNAME);
		info.setProperty("password", PASSWORD);
		try {
			return DriverManager.getConnection(URL, info);
		} catch (SQLException e) {
			throw new UserDaoException(e);
		}
	}

	public void releaseConnection(Connection connection) throws UserDaoException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new UserDaoException(e);
		}
	}
	
}
