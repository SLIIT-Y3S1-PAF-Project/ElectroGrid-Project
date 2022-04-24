package com;

import model.Branch;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
//import javax.validation.constraints.Pattern;
@Path("/Branches")

public class BranchService {
	Branch branchObj = new Branch();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBranches()
	{
	return branchObj.readBranches();
	}


@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertBranch(
//@Pattern(regexp="^[a-z A-Z 0-9]*$") @NotEmpty(message = "Branch ID can't be empty")
@FormParam("branchID") String branchID,		
@FormParam("branchName") String branchName,
@FormParam("branchAddress") String branchAddress,
@FormParam("branchContact") int branchContact,
@FormParam("branchEmail") String branchEmail)
{
String output = branchObj.insertBranch(branchID, branchName, branchAddress, branchContact,branchEmail);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateBranch(String branchData)
{
//Convert the input string to a JSON object
JsonObject branchObject = new JsonParser().parse(branchData).getAsJsonObject();
//Read the values from the JSON object
String branchID = branchObject.get("branchID").getAsString();
String branchName = branchObject.get("branchName").getAsString();
String branchAddress = branchObject.get("branchAddress").getAsString();
String branchContact = branchObject.get("branchContact").getAsString();
String branchEmail = branchObject.get("branchEmail").getAsString();
String output = branchObj.updateBranch(branchID, branchName, branchAddress, branchContact, branchEmail);
return output;
}




@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteBranch(String branchData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(branchData, "", Parser.xmlParser());

//Read the value from the element <itemID>
String branchID = doc.select("branchID").text();
String output = branchObj.deleteBranch(branchID);
return output;
}
}