package app.components;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.FoodItem;
import app.entity.FoodStall;
import app.repository.FoodItemRepository;
import app.repository.FoodStallRepository;

@Component
public class FoodItemComponent {

	@Autowired
	private FoodItemRepository itemRepo;
	
	@Autowired
	private FoodStallRepository stallRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	public List<FoodItem> getAllFoods(){
		return itemRepo.findAll();
	}
	
	public List<FoodItem> getStallFoods(String stallName) {
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("%s is not in the database.", stallName)
			);
		}
		return itemRepo.findByStallId(stall.getStallId());
	}
	
	public String addNewFoodItem(String stallName, String itemName, Double price){
		return "This 'food/new' URL will add a new food item with the following information: "
				+ "\nstallName = " + stallName 
				+ "\nitemName = " + itemName
				+ "\nprice = " + String.valueOf(price);
	}
	
	
	
}
