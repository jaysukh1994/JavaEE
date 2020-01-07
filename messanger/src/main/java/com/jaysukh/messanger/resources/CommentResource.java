package com.jaysukh.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jaysukh.messanger.model.Comment;
import com.jaysukh.messanger.service.CommentService;

@Path("/") //Sub resource or child resource url
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	
	
	private CommentService commentService = new CommentService();
	
	
	/*
	 
	@GET
	public String getString()
	{
		return "test";
	}
	@GET
	@Path("/{commentId}")//Here we can get parent resource url id as well , See the example here message id
	public String getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId)
	{
		return "commentId: "+ commentId + " messageId: "+ messageId;
	}
	*/
	
	@GET
	public List<Comment> getComment(@PathParam("messageId") long messageId)
	{
		System.out.print("Message Id"+ messageId);
	   return commentService.getAllComments(messageId);
	}
	
	
	@GET
	@Path("/{commentId}")//Here we can get parent resource url id as well , See the example here message id
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId)
	{
		return commentService.getComment(messageId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId , Comment comment)
	{
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	public Comment updateCommnet(@PathParam("messageId")long messageId, Comment comment)
	{
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId")long messageId,@PathParam("commentId") long commentId)
	{
		commentService.removeComment(messageId, commentId);
	}

	
}
