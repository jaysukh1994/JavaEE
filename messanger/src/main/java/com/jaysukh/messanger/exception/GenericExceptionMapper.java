package com.jaysukh.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// GenericExceptionMapper will execute only if there is no other exception related mapper not found

//@Provider //Just need to comment annotation disble this mapper
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		
		
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "https://www.google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage)
				        .build();
	}

	
}
