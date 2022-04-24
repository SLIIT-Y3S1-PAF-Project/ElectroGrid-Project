package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Message {
	//A common method to connect to the DB
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");

	//Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}

	public String readMessages()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Name</th><th>Contact No</th>" +
			 "<th>Email</th>" + 
			 "<th>Message</th>" +
			 "<th>Update</th><th>Remove</th></tr>";

	String query = "select * from messages";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
		 String messageID = Integer.toString(rs.getInt("messageID"));
		 String name = rs.getString("name"); 
		 String contactNo = Integer.toString(rs.getInt("contactNo")); 
		 String email = rs.getString("email");
		 String message = rs.getString("message");
	// Add into the html table
	output += "<tr><td>" +  name + "</td>";
	output += "<td>" + contactNo + "</td>";
	output += "<td>" + email + "</td>";
	output += "<td>" + message + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='messages.jsp'>"+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	+ "<input name='messageID' type='hidden' value='" + messageID
	+ "'>" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the messages.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	

public String insertMessage(String name, int contactNo, String email, String message)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into messages(`messageID`,`name`,`contactNo`,`email`,`message`)"
+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, name);
preparedStmt.setInt(3, contactNo);
preparedStmt.setString(4, email);
preparedStmt.setString(5, message);
// execute the statement



preparedStmt.execute();
con.close();
output = "Inserted successfully";
}
catch (Exception e)
{
output = "Error while inserting the message.";
System.err.println(e.getMessage());
}
return output;
}
}