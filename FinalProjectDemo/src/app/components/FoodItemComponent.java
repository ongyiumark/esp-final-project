package app.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.FoodItemRepository;

@Component
public class FoodItemComponent {

	@Autowired
	private FoodItemRepository fir;
	
	public String foodList(){
		//Incomplete
		//Must find iterate to every stall and show their list
		String response = "Stall name \nFoodList";
		
		return response;
	}
	
	public String stallFoodList(String stallName) {
		String response = "Stall name \nFoodList";
		
		return response;
	}
	
	public String addNewFoodItem(String stallName, String itemName, double price){
		String response = "New food item successfully added";
		
		return response;
	}
	
	
	
}
