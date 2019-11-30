package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Inspector {
	public class AccidentDetails {
		public String accidentID;
		public String type;
		public String city;
		public String date;
	}
	public int submitInitialReport(int policyID, int inspectorID, int carCondition, String comments) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO `InitialReport` VALUES (?, ?, ?, ?, ?);");
			statement.setInt(1, policyID);
			statement.setInt(2, inspectorID);
			statement.setInt(3, carCondition);
			statement.setString(4, "WAIT_DEC");
			statement.setString(5, comments);
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
	public int submitAccidentReport(int claimID, String damageDetails, String comments) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `AccidentReport` SET `status` = ?, `damageDetails` = ?, `comments` = ? WHERE `AccidentReport`.`claimID` = ?");
			statement.setString(1, "WAIT_DEC");
			statement.setString(2, damageDetails);
			statement.setString(3, comments);
			statement.setInt(4, claimID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}

	public String[] getPendingInitialAssignedInspections(int inspectorID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement stmt =  con.prepareStatement("SELECT `policyID` FROM `InitialReport` WHERE `inspectorID` = ? AND `status` = 'PEND_INS'");
			stmt.setInt(1, inspectorID);
			ResultSet rs = stmt.executeQuery();
			int rowCount = 0;
			if (rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst();
			}
			String[] resultList = new String[rowCount];
			int index = 0;
			while(rs.next()) {
				resultList[index] = String.valueOf(rs.getInt("policyID"));
			}
			return resultList;
		} catch (Exception e) {
			return null;
		}
	}
	public String[] getPendingAccidentAssignedInspections(int inspectorID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement stmt =  con.prepareStatement("SELECT `claimID` FROM `AccidentReport` WHERE `inspectorID` = ? AND `status` = 'PEND_INS'");
			stmt.setInt(1, inspectorID);
			ResultSet rs = stmt.executeQuery();
			int rowCount = 0;
			if (rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst();
			}
			String[] resultList = new String[rowCount];
			int index = 0;
			while(rs.next()) {
				resultList[index] = String.valueOf(rs.getInt("claimID"));
			}
			return resultList;
		} catch (Exception e) {
			return null;
		}
	}
}
