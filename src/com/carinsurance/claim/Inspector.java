package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Inspector {
	public int submitInitialReport(String policyID, String inspectorID, int carCondition, String comments) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO `InitialReport` VALUES (?, ?, ?, ?, ?);");
			statement.setString(1, policyID);
			statement.setString(2, inspectorID);
			statement.setInt(3, carCondition);
			statement.setString(4, "WAIT_DEC");
			statement.setString(5, comments);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	public int submitAccidentReport(String claimID, String damageDetails, String comments) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `AccidentReport` SET `status` = ?, `damageDetails` = ?, `comments` = ? WHERE `AccidentReport`.`claimID` = ?");
			statement.setString(1, "WAIT_DEC");
			statement.setString(2, damageDetails);
			statement.setString(3, comments);
			statement.setString(4, claimID);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
//	TODO Change return type
	public int getAccidentDetails(String accidentID) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Accident");
//			TODO Update Query
			statement.setString(1, accidentID);
			class AccidentDetails {
				public String accidentID;
				public String type;
				public String city;
				public String date;
			}
			
			AccidentDetails ac;
			ac.accidentID = "1";
			ac.city = "Lahore";
			ac.date = "2009-10-12";
			ac.
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
			}
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
//	TODO Change return type
	public int getCarDetails(String carID) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO AccidentReport VALUES (?, ?)");
			statement.setString(1, claimID);
			statement.setString(2, inspectorID);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
//	TODO Change return type
	public int getClientDetails(String clientID) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE AccidentReport SET `decision` = ?, `clientPayment` = ?, `companyPayment` = ? WHERE `AccidentReport`.`claimID` = ?");
			statement.setString(1, decision);
			statement.setString(2, clientPayment);
			statement.setString(3, companyPayment);
			statement.setString(2, claimID);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
//	TODO change return type
	public int getAssignedInspections(String inspectorID) {
		return 0;
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
