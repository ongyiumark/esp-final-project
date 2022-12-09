package app.components;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.FoodItem;
import app.entity.FoodStall;
import app.entity.Session;
import app.repository.FoodItemRepository;
import app.repository.FoodStallRepository;
import app.repository.SessionRepository;

@Component
public class FoodItemComponent {

	@Autowired
	private FoodItemRepository itemRepo;
	
	@Autowired
	private FoodStallRepository stallRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	public List<FoodItem> getAllFoods(){
		return itemRepo.findAll();
	}
	
	public List<FoodItem> getStallFoods(String stallName) {
		// Find the food stall corresponding to `stallName`
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("Food stall '%s' is not in the database.", stallName)
			);
		}
		
		return itemRepo.findByStallId(stall.getStallId());
	}
	
	public FoodItem addNewFoodItem(String sessionKey, String stallName, String itemName, Double price){
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException(
				String.format("Invalid session key.")
			);
		}
		
		// Find the food stall corresponding to `stallName`
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("Food stall '%s' is not in the database.", stallName)
			);
		}
		
		// Check if food item already exists in the same stall
		FoodItem item = itemRepo.findByItemNameAndStallId(itemName, stall.getStallId());
		
		// Instantiate new food item if it is not in the database
		if (item == null) item = new FoodItem();
		
		// Update properties
		item.setItemName(itemName);
		item.setStallId(stall.getStallId());
		item.setPrice(price);
		
		// Update database
		item = itemRepo.save(item);
		
		return item;
	}
	
	
	
}
