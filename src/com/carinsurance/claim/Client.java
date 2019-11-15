package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Client {
	public int createClient(String name, String address, String phone, String email, String dateOfBirth) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Client VALUES (?, ?, ?, ?, ?, ?)");
			String id = "1124";
//			TODO : Create Customer ID
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, address);
			statement.setString(4, phone);
			statement.setString(5, email);
			statement.setDate(6, java.sql.Date.valueOf(dateOfBirth));
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.print(e);
			return -1;
		}
	}
	public int createCar(String registrationNumber, String makeCompany, String registrationYear, String clientID, String makeYear) throws Exception{
		try{
			Connection con = getConnection();
//			 TODO : Create CarID
			String id = "2";
			PreparedStatement statement = con.prepareStatement("INSERT INTO Car VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, registrationNumber);
			statement.setString(3, makeCompany);
			statement.setString(4, registrationYear);
			statement.setString(5, clientID);
			statement.setString(6, makeYear);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	public int createPolicyClient(String carID) throws Exception{
		try {
			Connection con = getConnection();
			// TODO : Create PolicyID
			String id = "2";
			PreparedStatement statement = con.prepareStatement("INSERT INTO `Policy` (`policyID`, `carID`) VALUES (?, ?)");
			statement.setString(1, id);
			statement.setString(2, carID);
			statement.execute();
			return 1;
		} catch(Exception e) {
			System.out.println(e);
			return -1;
		}
	}
	public int createAccident(String type, String city, String date) {
		try {
			Connection con = getConnection();
			// TODO : Create PolicyID
			String id = "1";
			PreparedStatement statement = con.prepareStatement("INSERT INTO Accident VALUES (?, ?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, type);
			statement.setString(3, city);
			statement.setDate(4, java.sql.Date.valueOf(date));
			statement.execute();
			return 1;
		} catch(Exception e) {
			System.out.println(e);
			return -1;
		}
	}
	public int createClaim(String policyID, String accidentID) {
		try {
			Connection con = getConnection();
			// TODO : Create ClaimID
			String id = "1";
			PreparedStatement statement = con.prepareStatement("INSERT INTO Claim VALUES (?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, policyID);
			statement.setString(3, accidentID);
			statement.execute();
			return 1;
		} catch(Exception e) {
			System.out.println(e);
			return -1;
		}
	}
	
	public String testConnection() throws Exception {
		try {
			System.out.println("Before Connection");
			Connection con = getConnection();
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
	private static Connection getConnection() throws Exception {
		try {
			String url = "jdbc:mysql://localhost:3306/carinsurance";
			String username = "root";
			String password = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}

