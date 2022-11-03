package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.LocationComponent;

@Path("/location")
public class LocationController {
	@Autowired
	LocationComponent lc;
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	public String newReview(@FormParam("locationName") String locationName,
							@FormParam("longitude") Double longitude,
							@FormParam("latitude") Double latitude) {
		return lc.addNewLocation(locationName, longitude, latitude);
	}
	
	@GET
	@Path("/list")
	public String reviewList() {
		return lc.getAllLocations();
	}
}
