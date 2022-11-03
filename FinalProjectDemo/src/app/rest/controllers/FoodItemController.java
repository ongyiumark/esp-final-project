package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.FoodItemComponent;

@Path("/food")
public class FoodItemController {

	@Autowired
	private FoodItemComponent fic;
	
	@GET
	@Path("/list")
	public String foodList() {
		return fic.getAllFoods();
	}
	
	@GET
	@Path("/stall")
	public String stallFoodList(@QueryParam("stallName") String stallName) {	
		return fic.getStallFoods(stallName);
	}
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newFoodItem(@FormParam("stallName") String stallName, 
							  @FormParam("itemName") String itemName, 
							  @FormParam("price") Double price) {
		
		return fic.addNewFoodItem(stallName, itemName, price);
	}
}
