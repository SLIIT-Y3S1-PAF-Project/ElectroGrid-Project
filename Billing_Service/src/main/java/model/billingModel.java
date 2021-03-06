package model;

import java.sql.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource; 

public class billingModel {

	//DB connection
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
	//Connection Details: DBServer/DBName, username, password 
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	 	} 
	
	 
	 //read the added bills from the DB
	 public String readBills() 
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
		 output = "<table border='1'><tr><th>Invoice No</th>"+
		 "<th>Date</th>" +
		 "<th>Customer Name</th>" + 
		 "<th>Account No</th>" +
		 "<th>Units</th>"+
		 "<th>Unit Price</th>"+
		 "<th>Tax</th>"+
		 "<th>Total Amount</th></tr>"; 
		 
		 String query = "select * from reg_cus_billing"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
	 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String invoiceNo = Integer.toString(rs.getInt("invoiceNo")); 
		 String date = rs.getString("date");
		 String cusName = rs.getString("cusName"); 
		 String accNo = Integer.toString(rs.getInt("accNo"));
		 String noOfUnits = Integer.toString(rs.getInt("noOfUnits")); 
		 String unitPrice = Double.toString(rs.getDouble("unitPrice")); 
		 String tax = Double.toString(rs.getDouble("tax")); 
		 String totalAmount = Double.toString(rs.getDouble("totalAmount")); 
	 
		 // Add into the html table
		 output += "<tr><td>" + invoiceNo + "</td>"; 
		 output += "<td>" + date + "</td>"; 
		 output += "<td>" + cusName + "</td>"; 
		 output += "<td>" + accNo + "</td>"; 
		 output += "<td>" + noOfUnits + "</td>"; 
		 output += "<td>" + unitPrice + "</td>"; 
		 output += "<td>" + tax + "</td>"; 
		 output += "<td>" + totalAmount + "</td>"; 
	 
		 con.close(); 
	 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading Bill Data."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	 
	 
	 //insert bills to the DB
	 public String insertBill(java.sql.Date date, String cusName, String accNo, String noOfUnits, String unitPrice, String tax, String totalAmount)
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
			 String query = " insert into reg_cus_billing(`invoiceNo`,`date`,`cusName`,`accNo`,`noOfUnits`,`unitPrice`,`tax`,`totalAmount`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			 preparedStmt.setDate(2, sqlDate);
			 preparedStmt.setString(3, cusName); 
			 preparedStmt.setString(4, accNo);
			 preparedStmt.setString(5, noOfUnits); 
			 preparedStmt.setDouble(6, Double.parseDouble(unitPrice)); 
			 preparedStmt.setDouble(7, Double.parseDouble(tax));
			 preparedStmt.setDouble(8, Double.parseDouble(totalAmount)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting Bill Data."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output;
	 }
	 
	 
	 //Update the bills already added to the DB
	 public String updateBill(String invoiceNo, String date, String cusName, String accNo, String noOfUnits, String unitPrice, String tax, String totalAmount) 
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
		 String query = "UPDATE reg_cus_billing SET   date=?, cusName=?, accNo=?, noOfUnits=?, unitPrice=?, tax=?, totalAmount=? WHERE invoiceNo=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		 // binding values
		 preparedStmt.setString(1, date); 
		 preparedStmt.setString(2, cusName); 
		 preparedStmt.setString(3, accNo);
		 preparedStmt.setString(4, noOfUnits); 
		 preparedStmt.setDouble(5, Double.parseDouble(unitPrice)); 
		 preparedStmt.setDouble(6, Double.parseDouble(tax));
		 preparedStmt.setDouble(7, Double.parseDouble(totalAmount));
		 preparedStmt.setInt(8, Integer.parseInt(invoiceNo));
		
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating Bill Data."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	 
	 
	 //Delete bills form the DB
		 public String deleteBill(String invoiceNo) 
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
			 String query = "delete from reg_cus_billing where invoiceNo=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(invoiceNo)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting Bill Data."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		}
		 
		 //inter service communication
		 public String readAllCusDetails(int cusId){
				Client c = Client.create();
				WebResource resource = c.resource("http://localhost:8095/BillingService/billingService/bills/readBills");
				String output = resource.get(String.class);
				
				return output;
			}
	 
	 
}
