package app.components;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.FoodItemRepository;

@Component
public class FoodItemComponent {

	@Autowired
	private FoodItemRepository itemRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String getAllFoods(){
		return "This 'food/list' URL will return a list of all food items";
	}
	
	public String getStallFoods(String stallName) {
		return "This 'food/stall' URL received the following information: "
				+ "\nstallName = " + stallName;
	}
	
	public String addNewFoodItem(String stallName, String itemName, Double price){
		return "This 'food/new' URL will add a new food item with the following information: "
				+ "\nstallName = " + stallName 
				+ "\nitemName = " + itemName
				+ "\nprice = " + String.valueOf(price);
	}
	
	
	
}
