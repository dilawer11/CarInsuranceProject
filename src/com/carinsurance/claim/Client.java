package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Client {
	public int createClient(String name, String address, String phone, String email, String dateOfBirth) throws Exception{
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Client VALUES (?, ?, ?, ?, ?, ?)");
			statement.setInt(1, 0);
			statement.setString(2, name);
			statement.setString(3, address);
			statement.setString(4, phone);
			statement.setString(5, email);
			statement.setDate(6, Date.valueOf(dateOfBirth));
			statement.execute();
			PreparedStatement stmt2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt2.executeQuery();
			int id = -1;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch(Exception e){
			return -1;
		}
	}
	public int createCar(String registrationNumber, String makeCompany, String registrationYear, int clientID, String makeYear) throws Exception{
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Car VALUES (?, ?, ?, ?, ?, ?)");
			statement.setInt(1, 0);
			statement.setString(2, registrationNumber);
			statement.setString(3, makeCompany);
			statement.setString(4, registrationYear);
			statement.setInt(5, clientID);
			statement.setString(6, makeYear);
			statement.execute();
			PreparedStatement stmt2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt2.executeQuery();
			int id = -1;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch(Exception e){
			return -1;
		}
	}
	public int createPolicyClient(int carID) throws Exception{
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO `Policy` (`policyID`, `carID`) VALUES (?, ?)");
			statement.setInt(1, 0);
			statement.setInt(2, carID);
			statement.execute();
			PreparedStatement stmt2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt2.executeQuery();
			int id = -1;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch(Exception e) {
			return -1;
		}
	}
	public int createAccident(int carID, String type, String city, String date) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Accident VALUES (?, ?, ?, ?, ?)");
			statement.setInt(1, 0);
			statement.setInt(2, carID);
			statement.setString(3, type);
			statement.setString(4, city);
			statement.setDate(5, java.sql.Date.valueOf(date));
			statement.execute();
			PreparedStatement stmt2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt2.executeQuery();
			int id = -1;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch(Exception e) {
			return -1;
		}
	}
	public int createClaim(int policyID, int accidentID) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Claim VALUES (NULL, ?, ?, 'submitted')");
			statement.setInt(1, policyID);
			statement.setInt(2, accidentID);
			statement.execute();
			PreparedStatement stmt2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rs = stmt2.executeQuery();
			int id = -1;
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch(Exception e) {
			return -1;
		}
	}
	public String getStatusAccident(int claimID) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT `status` FROM `Claim` WHERE `claimID` = ?");
			statement.setInt(1, claimID);
			ResultSet rs = statement.executeQuery();
			String returnStatus = "";
			while(rs.next()) {
				returnStatus = rs.getString("status");
			}
			return returnStatus;
		} catch(Exception e) {
			return "";
		}
	}
	public String getStatusPolicyApplication(int policyID) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT status FROM Policy WHERE policyID = ?");
			statement.setInt(1, policyID);
			ResultSet rs = statement.executeQuery();
			String returnStatus = "";
			while(rs.next()) {
				returnStatus = rs.getString("status");
			}
			return returnStatus;
		} catch(Exception e) {
			return null;
		}
	}

}

