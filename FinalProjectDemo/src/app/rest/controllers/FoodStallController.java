package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.FoodStallComponent;
import app.entity.FoodStall;

@Path("/stall")
public class FoodStallController {
	@Autowired
	private FoodStallComponent fsc; 
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FoodStall> getAllStalls() {
		return fsc.getAllStalls();
	}
	
	@POST
	@Path("/new")    
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public FoodStall addNewFoodStall(
			@FormParam("sessionKey") String sessionKey,
			@FormParam("stallName") String stallName,
			@FormParam("description") String description,
			@FormParam("locationName") String locationName,
			@FormParam("imageName") String imageName,
 			@FormParam("longitude") Double longitude,
			@FormParam("latitude") Double latitude) {
		return fsc.addNewFoodStall(sessionKey, stallName, description, imageName, locationName, longitude, latitude);
	}
}
