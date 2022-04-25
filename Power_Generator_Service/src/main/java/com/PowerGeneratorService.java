package com;
import model.PowerGenerator;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/PowerGenerators")
public class PowerGeneratorService {
	
	PowerGenerator pGen = new PowerGenerator(); 

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPowerGenerator(@FormParam("gCode") String gCode, 
	 @FormParam("gName") String gName, 
	 @FormParam("gType") String gType, 
	 @FormParam("gLocation") String gLocation,
	 @FormParam("gUnitPrice") String gUnitPrice,
	 @FormParam("gRegDate") Date gRegDate) 
	{ 
		 String output = pGen.addPowerGenerator(gCode, gName, gType, gLocation, gUnitPrice, gRegDate); 
		 return output; 
	}
	
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPowerGenerators() 
	{ 
		return pGen.readPowerGenerators(); 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePowerGenerator(String powerGeneratorData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject powerGen = new JsonParser().parse(powerGeneratorData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String gID = powerGen.get("gID").getAsString(); 
	 String gCode = powerGen.get("gCode").getAsString(); 
	 String gName = powerGen.get("gName").getAsString(); 
	 String gType = powerGen.get("gType").getAsString(); 
	 String gLocation = powerGen.get("gLocation").getAsString(); 
	 String gUnitPrice = powerGen.get("gUnitPrice").getAsString(); 
	 String gRegDate = powerGen.get("gRegDate").getAsString(); 
	 
	 String output = pGen.updatePowerGenerator( gID, gCode, gName, gType, gLocation, gUnitPrice, gRegDate); 
	return output; 
	}	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePowerGenerator(String powerGeneratorData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(powerGeneratorData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String gID = doc.select("gID").text(); 
	 String output = pGen.deletePowerGenerator(gID); 
	return output; 
	}
	
	
	//login
	@POST
	@Path("/login") 
	@Produces(MediaType.TEXT_HTML) 
	public String login(@FormParam("username") String username, 
			 @FormParam("password") String password)
	{ 
		String output = pGen.login(username, password);
		return output;
	}
}
