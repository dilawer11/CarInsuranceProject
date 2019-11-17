package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Admin {
	public int createInspector(String name, String phone, String email) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO Inspector VALUES (?, ?, ?, ?)");
			statement.setInt(1, 0);
			statement.setString(2, name);
			statement.setString(3, phone);
			statement.setString(4, email);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int assignInspectorInitialReport(int policyID, int inspectorID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO InitialReport VALUES (?, ?)");
			statement.setInt(1, policyID);
			statement.setInt(2, inspectorID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int createDecisionPolicy(int policyID, String title, String description, int coverage, int charge, String status) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `Policy` SET `title` = ?, `description` = ?, `coverage` = ?, `charge` = ?, `status` = ? WHERE `Policy`.`policyID` = ?");
			statement.setString(1, title);
			statement.setString(2, description);
			statement.setInt(3, coverage);
			statement.setInt(4, charge);
			statement.setString(5, status);
			statement.setInt(6, policyID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int assignInspectorAccidentReport(int claimID, int inspectorID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO AccidentReport VALUES (?, ?)");
			statement.setInt(1, claimID);
			statement.setInt(2, inspectorID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int createDecision(int claimID, String decision, String clientPayment, String companyPayment) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE AccidentReport SET `decision` = ?, `clientPayment` = ?, `companyPayment` = ? WHERE `AccidentReport`.`claimID` = ?");
			statement.setString(1, decision);
			statement.setString(2, clientPayment);
			statement.setString(3, companyPayment);
			statement.setInt(2, claimID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int deactivatePolicy(int policyID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `Policy` SET `status` = 'inactive' WHERE `Policy`.`policyID` = ?");
			statement.setInt(1, policyID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
	public int activatePolicy(int policyID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE `Policy` SET `status` = 'active' WHERE `Policy`.`policyID` = ?");
			statement.setInt(1, policyID);
			statement.execute();
			return 1;
		} catch(Exception e){
			return -1;
		}
	}
}
