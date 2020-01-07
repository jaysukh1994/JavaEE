package com.jaysukh.messanger.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

@Path("injectiondemo")
public class InjectResource {

	//Example : Diffrent type of Param annotation
	// 1. Metrix param url(For Query parameter using ;(semicolon) instead of ?)
	//http://localhost:8080/messanger/webapi/injectiondemo/annotation;param=value
	
	
	//2. HeaderParam
	//Passed custom header parameter, we can access using @HeaderParam annotation
	
	
	//3. CookieParam
	// We can access cookie which is sent in request by using @CookieParam annotation
	
	// 4 @FormParam
	// This is not wisely used, but we can access form which is submittted in post request
	@GET
	@Path("annotation")
	public String getParamUsingAnnotation(@MatrixParam("paramName") String paramValue
			                              ,@HeaderParam("headerParamName") String headerParamValue
			                              ,@CookieParam("cookieName") String cookieValue)
	{
		
		return "paramName: " + paramValue + "  headerParamName: " + headerParamValue +" cookieName" +cookieValue;
	}
	
	
	//2. way to access param : If you don't know the name of param or you have more number of param then you can use context param and loop 
	//throw all param and value
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo info, @Context HttpHeaders header)
	{
		
		String path = info.getAbsolutePath().toString();
		String cookies = header.getCookies().toString();
		return "Path : "+path +" cookies "+ cookies ;
	}
}
