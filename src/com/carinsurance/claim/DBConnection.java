package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	public String testConnection() throws Exception {
		try {
			System.out.println("Before Connection");
			Connection con = DBConnection.getConnection();
			System.out.println("After Connection");
//			PreparedStatement posted = con.prepareStatement("SELECT * FROM Client");
			Statement stmt = con.createStatement();
			System.out.println("After Prepare");
			ResultSet rs = stmt.executeQuery("SELECT * FROM Client");
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
			System.out.println("Executed");
			con.close();
			return "Done";
			
		} catch (Exception e) {
			System.out.println(e);
			return "Not Done";
			
		}
	}
	static Connection getConnection() throws Exception {
		try {
			String url = "jdbc:mysql://localhost:3306/carinsurance";
			String username = "webapi";
			String password = "instance1";
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(url);
			System.out.println(username);
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
