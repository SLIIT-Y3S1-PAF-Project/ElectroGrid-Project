package model;

import java.sql.*;


public class PowerGenerator {
	
	//DB connection
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
				 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_grid", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return con; 
	} 
		
	//insert
	public String addPowerGenerator(String gCode, String gName, String gType, String gLocation, String gUnitPrice, java.sql.Date gRegDate) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			 
			// create a prepared statement
			String query = " insert into powergenerators (`gID`,`gCode`,`gName`,`gType`,`gLocation`, `gUnitPrice`, gRegDate)" + " values (?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, gCode); 
			preparedStmt.setString(3, gName); 
			preparedStmt.setString(4, gType); 
			preparedStmt.setString(5, gLocation); 
			preparedStmt.setDouble(6, Double.parseDouble(gUnitPrice)); 
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			preparedStmt.setDate(7, sqlDate);
			//preparedStmt.setDate(7, gRegDate); 
			
			//validations
			if(gCode.isEmpty()||gName.isEmpty()||gType.isEmpty()||gLocation.isEmpty()||gUnitPrice.isEmpty()) 
			{
				return "Please provide values for all fields";
			}
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while adding the power generator."; 
			 System.err.println(e.getMessage()); 
		} 
		
		return output; 
	} 
	
	
	//read
	public String readPowerGenerators() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for reading."; 
		 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Power Generator Code</th><th>Power Generator Name</th>" +
	 "<th>Power Generator Type</th>" + 
	 "<th>Power Generator Location</th>" +
	 "<th>Unit Price</th>" +
	 "<th>Registered Date/Modified Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from powergenerators"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String gID = Integer.toString(rs.getInt("gID")); 
	 String gCode = rs.getString("gCode"); 
	 String gName = rs.getString("gName"); 
	 String gType = rs.getString("gType");
	 String gLocation = rs.getString("gLocation");
	 String gUnitPrice = Double.toString(rs.getDouble("gUnitPrice")); 
	 String gRegDate = rs.getString("gRegDate");
	 // Add into the html table
	 output += "<tr><td>" + gCode + "</td>"; 
	 output += "<td>" + gName + "</td>"; 
	 output += "<td>" + gType + "</td>"; 
	 output += "<td>" + gLocation + "</td>"; 
	 output += "<td>" + gUnitPrice + "</td>"; 
	 output += "<td>" + gRegDate + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='powergenerators.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + gID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the power generators."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	//delete
	public String deletePowerGenerator(String gID) 
	 { 
		String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from powergenerators where gID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(gID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the power generator."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	 } 
	
	
	//update
	public String updatePowerGenerator(String gID, String gCode, String gName, String gType, String gLocation, String gUnitPrice, String gRegDate) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			// create a prepared statement
			String query = "UPDATE powergenerators SET gCode=?,gName=?,gType=?,gLocation=?,gUnitPrice=?,gRegDate=?  WHERE gID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, gCode); 
			preparedStmt.setString(2, gName);
			preparedStmt.setString(3, gType);
			preparedStmt.setString(4, gLocation);
			preparedStmt.setDouble(5, Double.parseDouble(gUnitPrice)); 
			preparedStmt.setString(6, gRegDate);  
			preparedStmt.setInt(7, Integer.parseInt(gID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the power generator."; 
			System.err.println(e.getMessage()); 
		} 
			return output; 
	} 
	
	
	public String deleteItem(String itemID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from items where itemID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 } 
	
	
	public String login(String username, String password) {
		String output = null;
		try
		{
			Connection con = connect();
			if (con == null) 
			{
				return "Error while connecting to the database";
			}
			 // create a prepared statement
			
			
			 String query = "select password from users where username=? and password=?"; 
			 PreparedStatement preparedStmt =  con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, username); 
			 preparedStmt.setString(2, password); 
			
			 if(preparedStmt != null) {
				 output =  "Login success";
			 }
			 else {
				 output = "Login failed";
			 }
			
			 con.close();
		}
		catch (Exception e) 
		{
			output = "Error in login function"; 
			System.err.println(e.getMessage()); 
		}
			
		return output;
	}

}
