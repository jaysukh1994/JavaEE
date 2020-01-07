package com.jaysukh.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.jaysukh.messanger.database.DatabaseClass;
import com.jaysukh.messanger.exception.DataNotFoundException;
import com.jaysukh.messanger.model.Message;

public class MessageService {

	
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L,new Message(1L, "Hello World","Jaysukh"));
		messages.put(2L,new Message(2L, "Hello Jersey","Jaysukh"));

	}
	public List<Message> getMessagesForYear(int year)
	{
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values())
		{
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year)
			{
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
		
	}
	
	public List<Message> getAllMessagesPagenated(int start,int size)
	{
		ArrayList<Message> messageslst = new ArrayList<Message>(messages.values());
		if(start+size > messageslst.size()) return new ArrayList<Message>();
		return messageslst.subList(start, start + size);
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>( messages.values());
	}
	
	//Exception handling
	public Message getMessage(long id) throws DataNotFoundException
	{
		Message message =  messages.get(id);
		if(message == null)
		{
			throw new DataNotFoundException("Message id "+ id +  " not found" );
		}
		return message;
		
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		if(message.getId() <= 0)
		{
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id)
	{
		return messages.remove(id);
	}
}
