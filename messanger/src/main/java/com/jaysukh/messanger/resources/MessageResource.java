package com.jaysukh.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jaysukh.messanger.model.Message;
import com.jaysukh.messanger.resources.bean.MessageFilterBean;
import com.jaysukh.messanger.service.MessageService;


//If class methods have repeated annotation we can add class level as below
//@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
//@Produces(MediaType.APPLICATION_JSON)// Return request type
@Path("/messages")
public class MessageResource {

	MessageService messageservice = new MessageService();
	
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
			                         @QueryParam("start") int start,
			                         @QueryParam("size") int size)//Same method using for Query Param
	{
		if(year>0)
		{
			return messageservice.getMessagesForYear(year);
		}
		if(start >= 0 && size >= 0)
		{
			return messageservice.getAllMessagesPagenated(start, size);
		}
		return messageservice.getAllMessages();
	}*/
	
	// 3rd way to access param using BeanParam
	//For example in below method beanParam implimented for above method
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterParam)//Same method using for Query Param
	{
		System.out.print("Request");
		if(filterParam.getYear() > 0)
		{
			System.out.print("Request Year");
			return messageservice.getMessagesForYear(filterParam.getYear());
		}
		if(filterParam.getStart() >= 0 && filterParam.getSize() >= 0)
		{
			System.out.print("Request pagenated");
			return messageservice.getAllMessagesPagenated(filterParam.getStart(), filterParam.getSize());
		}
		return messageservice.getAllMessages();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
	@Produces(MediaType.APPLICATION_JSON)// Return request type
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,Message message)
	{
		message.setId(id);
		return messageservice.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId)
	{
		messageservice.removeMessage(messageId);
	}
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
	@Produces(MediaType.APPLICATION_JSON)// Return request type
	public Message addMessage(Message message) {//JSON Message automatically converted to Java Message object
		
		return messageservice.addMessage(message);
	} */
	
	
	//Return status code  in header using Response builder
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
	@Produces(MediaType.APPLICATION_JSON)// Return request type
	public Response addMessage(Message message) {
		System.out.println("Post request received");
		Message msessage = messageservice.addMessage(message);
		
		return Response.status(Status.CREATED)
		        .entity(msessage)
		        .build();
		
	}*/
	
	
	//Return status code  and location uri in header using Response builder and context annotation param
	@POST
	@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
	@Produces(MediaType.APPLICATION_JSON)// Return request type
	public Response addMessage(Message message,@Context UriInfo uriInfo) {
		
		
		Message newMsessage = messageservice.addMessage(message);
		String newId = String.valueOf(newMsessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri) //Here created method will add location uri as well as status code 201 in response header
		        .entity(newMsessage)
		        .build();
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId)//Here jersey convert PathParam variable to method accepted(or required) variable type
	{
		return messageservice.getMessage(messageId);
	}
	
	@Path("/{messageId}/comments") // Path routing // Return type will Sub resource type There will be no get or POST annotation
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
}
