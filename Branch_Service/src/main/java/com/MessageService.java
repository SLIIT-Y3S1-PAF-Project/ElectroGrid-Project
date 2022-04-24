package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Message;

@Path("/Messages")

public class MessageService {
	
	Message messageObj = new Message();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readMessages()
	{
	return messageObj.readMessages();
	}
	

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertMessage(@FormParam("name") String name,
@FormParam("contactNo") int contactNo,
@FormParam("email") String email,
@FormParam("message") String message)
{
String output = messageObj.insertMessage(name, contactNo, email, message);
return output;
}

}