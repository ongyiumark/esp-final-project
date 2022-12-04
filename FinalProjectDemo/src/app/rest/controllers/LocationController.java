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

import app.components.LocationComponent;
import app.entity.Location;

@Path("/location")
public class LocationController {
	@Autowired
	LocationComponent lc;
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Location addNewLocation(@FormParam("locationName") String locationName,
							@FormParam("longitude") Double longitude,
							@FormParam("latitude") Double latitude) {
		return lc.addNewLocation(locationName, longitude, latitude);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getAllLocations() {
		return lc.getAllLocations();
	}
}
