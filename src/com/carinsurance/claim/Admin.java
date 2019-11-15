package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Admin {
	public int createInspector(String name, String phone, String email) {
		try{
			Connection con = getConnection();
//			 TODO : Create InspectorID
			String id = "2";
			PreparedStatement statement = con.prepareStatement("INSERT INTO Inspector VALUES (?, ?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, phone);
			statement.setString(4, email);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	public int assignInspectorInitialReport(String policyID, String inspectorID) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO InitialReport VALUES (?, ?)");
			statement.setString(1, policyID);
			statement.setString(2, inspectorID);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	public int createDecisionPolicy(String policyID, String title, String description, int coverage, int charge, String status) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `Policy` SET `title` = ?, `description` = ?, `coverage` = ?, `charge` = ?, `status` = ? WHERE `Policy`.`policyID` = ?");
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setInt(3, coverage);
			statement.setInt(4, charge);
			statement.setString(5, status);
			statement.setString(6, policyID);
			statement.execute();
			return 1;
		} catch(Exception e){
			System.out.println(e);
			return -1;
		}
	}
	public int assignInspectorAccidentReport(String claimID, String inspectorID) {
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
	public int createDecision(String claimID, String decision, String clientPayment, String companyPayment) {
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
