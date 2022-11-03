package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.FoodStallComponent;

@Path("/stall")
public class FoodStallController {
	@Autowired
	private FoodStallComponent fsc; 
	
	@GET
	@Path("/stallList")   
	public String stallList() {
		return fsc.stallList();
	}
	
	@POST
	@Path("/new")    
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newstall(
			@FormParam("stallName") String stallname,
			@FormParam("description") String description,
			@FormParam("locationName") String locationName,
			@FormParam("longitude") Double longitude,
			@FormParam("latitude") Double latitude) {
		return fsc.addNewFoodStall(stallname,description,locationName,longitude,latitude);
	}
}