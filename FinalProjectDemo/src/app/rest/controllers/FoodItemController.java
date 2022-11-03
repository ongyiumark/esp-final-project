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

@Path("food")
public class FoodItemController {

	@Autowired
	private FoodItemComponent fic;
	
	@GET
	@Path("/foodList")
	public String foodListStatus() throws Exception
	{
		return fic.foodList();
	}
	
	@GET
	@Path("/foodList/stall")
	public String stallFoodList(@QueryParam("Stall Name")String stallName) {
		
		return fic.stallFoodList(stallName);
	}
	
	@POST
	@Path("/newFoodItem")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newFoodItem(@FormParam("Stall Name")String stallName, 
							  @FormParam("Item Name")String itemName, 
							  @FormParam("Price")int price) {
		
		return fic.addNewFoodItem(stallName, itemName, price);
	}
}
