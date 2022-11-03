package app.components;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.FoodItemRepository;

@Component
public class FoodItemComponent {

	@Autowired
	private FoodItemRepository foodRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String foodList(){
		return "This URL will return a list of all food items";
	}
	
	public String stallFoodList(String stallName) {
		return "This URL will return a list of food items from " 
					+ stallName;
	}
	
	public String addNewFoodItem(String stallName, String itemName, Double price){
		return "This URL will add a new food item with the following information: "
				+ "\nstallName = " + stallName 
				+ "\nitemName = " + itemName
				+ "\nprice = " + String.valueOf(price);
	}
	
	
	
}
