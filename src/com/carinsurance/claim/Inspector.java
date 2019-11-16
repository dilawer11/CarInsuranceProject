package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Inspector {
	class AccidentDetails {
		public String accidentID;
		public String type;
		public String city;
		public String date;
	}
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
	public ArrayList<AccidentDetails> getAccidentDetails(String accidentID) {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Accident WHERE accidentID = ?");
//			TODO Update Query
			statement.setString(1, accidentID);
			ArrayList<AccidentDetails> resultList;
			int index = 0;
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				AccidentDetails ac;
				ac.accidentID = rs.getString("accidentID");
				ac.city = rs.getString("city");
				ac.date = rs.getDate("date");
				ac.type = rs.getString("type");
				resultList.add(index, ac);
				index++;
			}
			return resultList;
		} catch(Exception e){
			System.out.println(e);
			ArrayList<AccidentDetails> resultList;
			return resultList;
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
		Connection con = getConnection();
		PreparedStatement initialReportStatement =  con.prepareStatement("SELECT * FROM InitialReport WHERE inspectorID = ? AND status ='PEND_INS'"); //TODO Check this Update for all other status
		PreparedStatement accidentReportStatement = con.prepareStatement("SELECT * FROM AccidentReport WHERE inspectorID = ? AND status = 'PEND_INS'");
		initialReportStatement.setString(1, inspectorID);
		accidentReportStatement.setString(1, inspectorID);
		ResultSet initialReportResultSet = executeQuery();
		ResultSet accidentReportResultSet = executeQuery();
		//TODO Compile into a result and send back
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
