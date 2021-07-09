package dev.hupp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			String endpoint = "database-1.cdr6fpewkz8c.us-east-1.rds.amazonaws.com";
			
			String url = "jdbc:postgresql://" + endpoint + "/postgres";
			String username = "hupp";
			String password = "password";
			
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
