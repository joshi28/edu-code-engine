package com.login.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseConnection {
	static Connection connection=null;
	public  static Connection getConnection() throws SQLException, ClassNotFoundException {
	
		
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/user_login_info?autoReconnect=true&useSSL=false","hbstudent","hbstudent");  
		return connection;

	}
	public static void closeConnection()  {
	
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement(PreparedStatement preparedStmt) {
		try {
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
