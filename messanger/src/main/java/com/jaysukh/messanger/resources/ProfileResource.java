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


import com.jaysukh.messanger.model.Profile;
import com.jaysukh.messanger.service.ProfileService;

@Consumes(MediaType.APPLICATION_JSON)//Accepting media Type
@Produces(MediaType.APPLICATION_JSON)// Return request type
@Path("/profiles")
public class ProfileResource {

	private ProfileService profileService = new ProfileService();
	
	
	@GET
	public List<Profile> getProfiles()
	{
		return profileService.getAllProfiles();
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateMessage(@PathParam("profileName") String profileName,Profile profile)
	{
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName)
	{
		profileService.removeProfile(profileName);
	}
	
	@POST
	public Profile addMessage(Profile profile) {//JSON Message automatically converted to Java Profile object
		return profileService.addProfile(profile);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getMessage(@PathParam("profileName") String profileName)//Here jersey convert PathParam variable to method accepted(or required) variable type
	{
		return profileService.getProfiles(profileName);
	}
}
