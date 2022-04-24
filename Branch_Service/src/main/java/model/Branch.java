package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Branch {
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
		
		public String readBranches()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Branch Name</th><th>Branch Address</th>" +
				"<th>Branch ContactNo</th>" +
				"<th>Branch Email</th>" +
				"<th>Update</th><th>Remove</th></tr>";

		String query = "select * from branches";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String branchID = rs.getString("branchID");
		String branchName = rs.getString("branchName");
		String branchAddress = rs.getString("branchAddress");
		String branchContact = Integer.toString(rs.getInt("branchContact"));
		String branchEmail = rs.getString("branchEmail");
		// Add into the html table
		output += "<tr><td>" + branchName + "</td>";
		output += "<td>" + branchAddress + "</td>";
		output += "<td>" + branchContact + "</td>";
		output += "<td>" + branchEmail + "</td>";
		// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		+ "<td><form method='post' action='branches.jsp'>"+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		+ "<input name='branchID' type='hidden' value='" + branchID
		+ "'>" + "</form></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the branches.";
		System.err.println(e.getMessage());
		}
		return output;
		}

		
		public String insertBranch(String id, String name, String address, int contact, String email)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " insert into branches(`branchID`,`branchName`,`branchAddress`,`branchContact`,`branchEmail`)"
		+ " values (?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, address);
		preparedStmt.setInt(4, contact);
		preparedStmt.setString(5, email);
		// execute the statement

		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the branches.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		public String updateBranch(String id, String name, String address, String contact, String email)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		// create a prepared statement
		String query = "UPDATE branches SET branchName=?,branchAddress=?,branchContact=?,branchEmail=?WHERE branchID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, address);
		preparedStmt.setString(3, contact);
		preparedStmt.setString(4, email);
		preparedStmt.setString(5, id);
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
		}
		catch (Exception e)
		{
		output = "Error while updating the item.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		public String deleteBranch(String branchID)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting."; }
		// create a prepared statement
		String query = "delete from branches where branchID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, branchID);
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
		output = "Error while deleting the branch.";
		System.err.println(e.getMessage());
		}
		return output;
		}
}