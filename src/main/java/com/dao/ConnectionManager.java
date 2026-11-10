package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	public static Connection getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3308/digital_banking";
		String username = "root";
		String password = "1234";

		Connection connection = null;

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
