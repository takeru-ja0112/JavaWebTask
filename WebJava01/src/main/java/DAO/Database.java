package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String URL = "jdbc:postgresql://localhost:5432/accountdb";
	private static final String USER = "takeru";
	private static final String PASSWORD = "takeru2003";
	
	public static Connection getConnection()throws SQLException {
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
}
