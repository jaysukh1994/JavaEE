package com.jaysukh.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jaysukh.messanger.database.DatabaseClass;
import com.jaysukh.messanger.exception.ErrorMessage;
import com.jaysukh.messanger.model.Comment;
import com.jaysukh.messanger.model.Message;



public class CommentService {

	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId)
	{
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		System.out.print(comments);
		return new ArrayList<Comment>(comments.values());
	}
	
	
	//Web application exception implementation in below method
	//Impliemnted below two way you can implement, but this is not good way
	public Comment getComment(long messageId,long commentId)
	{
		
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "https://www.google.com");
		Response response= Response.status(Status.NOT_FOUND).entity(errorMessage)
				        .build();
		
		Message message = messages.get(messageId);
		if(message == null)
		{
			throw new WebApplicationException(response);//WebApplicationException  class
		}
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		
	    Comment comment = comments.get(commentId);
		if(comment == null)
		{
			throw new NotFoundException(response); //WebApplicationException sub class
		}
	    return comment;
	}
	
	public Comment addComment(long messageId, Comment comment)
	{
		
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(messageId, comment);
		return comment;
		
	}
	
	public Comment updateComment(long messageId, Comment comment)
	{
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		
		if(comment.getId() <= 0)
		{
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
		
	}
	
	public Comment removeComment(long messageId,long commentId)
	{
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
		
	}
}
