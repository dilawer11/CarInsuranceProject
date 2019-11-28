package com.carinsurance.claim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Common {
	public String[] getCarDetails(int carID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT `registrationNumber`, `makeCompany`, `registrationYear`, `clientID`, `makeYear` FROM `Car` WHERE `carID` = ?");
			statement.setInt(1, carID);
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount =rsmd.getColumnCount();
			String[] resultList = new String[colCount];
			while(rs.next()) {
				resultList[0] = rs.getString("registrationNumber");
				resultList[1] = rs.getString("makeCompany");
				resultList[2] = rs.getString("registrationYear");
				resultList[3] = String.valueOf(rs.getInt("clientID"));
				resultList[4] = rs.getString("makeYear");
			}
			return resultList;
		} catch(Exception e){
			return null;
		}
	}
	public String[] getClientDetails(int clientID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Client WHERE clientID = ?");
			statement.setInt(1, clientID);
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount =rsmd.getColumnCount();
			String[] resultList = new String[colCount];
			while(rs.next()) {
				resultList[0] = rs.getString("name");
				resultList[1] = rs.getString("address");
				resultList[2] = rs.getString("phone");
				resultList[3] = rs.getString("email");
			}
			return resultList;
		} catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	public String[] getAccidentDetails(int accidentID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT `carID`, `type`, `city`, `date` FROM `Accident` WHERE `accidentID` = ?");
			statement.setInt(1, accidentID);
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount =rsmd.getColumnCount();
			String[] resultList = new String[colCount];
			while(rs.next()) {
				resultList[0] = String.valueOf(rs.getInt("carID"));
				resultList[1] = rs.getString("city");
				resultList[2] = rs.getString("date");
				resultList[3] = rs.getString("type");
			}
			return resultList;
		} catch(Exception e){
			return null;
		}
	}
	public String[] getPolicyDetails(int policyID) {
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Policy WHERE policyID = ?");
			statement.setInt(1, policyID);
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount =rsmd.getColumnCount();
			String[] resultList = new String[colCount];
			while(rs.next()) {
				resultList[0] = rs.getString("title");
				resultList[1] = rs.getString("description");
				resultList[2] = String.valueOf(rs.getInt("coverage"));
				resultList[3] = String.valueOf(rs.getInt("carID"));
				resultList[4] = String.valueOf(rs.getInt("charge"));
				resultList[5] = rs.getString("status");
			}
			return resultList;
		} catch(Exception e){
			return null;
		}
	}

}
