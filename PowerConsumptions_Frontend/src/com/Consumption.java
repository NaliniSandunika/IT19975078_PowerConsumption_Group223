package com;

import java.sql.*;

public class Consumption {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String readConsumptions() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table width=50%;><tr><th>Account Number</th><th>PremisesID</th><th>Month</th>"
					+ "<th>Date</th><th>Units</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from consumption";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String consumptionID = Integer.toString(rs.getInt("consumptionID"));
				String accountNumber = Integer.toString(rs.getInt("accountNumber"));
				String premisesID = rs.getString("premisesID");
				String month = rs.getString("month");
				String date = rs.getString("date");
				String units = Integer.toString(rs.getInt("units"));
				// Add into the html table
				output += "<tr><td><input id='hidConsumptionIDUpdate' name='hidConsumptionIDUpdate' type='hidden' value='" + consumptionID
						+ "'>" + accountNumber + "</td>";
				output += "<td>" + premisesID + "</td>";
				output += "<td>" + month + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + units + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-consumptionid='"
						+ consumptionID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Consumptions.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String insertConsumption(String accountnumber, String premisesid, String mont, String dat, String unit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into consumption(`consumptionID`,`accountNumber`,`premisesID`,`month`,`date`,`units`)"

					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(accountnumber));
			preparedStmt.setString(3, premisesid);
			preparedStmt.setString(4, mont);
			preparedStmt.setString(5, dat);
			preparedStmt.setInt(6, Integer.parseInt(unit));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newConsumptions = readConsumptions();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumptions + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the consumption.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateConsumption(String ID, String accountnumber, String premisesid, String mont, String dat, String unit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE consumption SET accountNumber=?,premisesID=?,month=?,date=?,units=? WHERE consumptionID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(accountnumber));
			preparedStmt.setString(2, premisesid);
			preparedStmt.setString(3, mont);
			preparedStmt.setString(4, dat);
			preparedStmt.setInt(5, Integer.parseInt(unit));
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newConsumptions = readConsumptions();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumptions + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the consumption.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteConsumption(String consumptionID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from consumption where consumptionID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(consumptionID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newConsumptions = readConsumptions();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumptions + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the consumption.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
