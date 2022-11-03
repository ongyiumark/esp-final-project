package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.FoodItem;
import app.entity.FoodStall;
import app.entity.Location;
import app.repository.FoodItemRepository;
import app.repository.FoodStallRepository;
import app.repository.LocationRepository;
import app.repository.ReviewRepository;
import app.repository.UserRepository;

@Component
public class DataSeeder {
	@Autowired
	private FoodStallRepository stallRepo;
	
	@Autowired
	private FoodItemRepository itemRepo;
	
	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@PostConstruct
	public void init() throws Exception {
		if (itemRepo.count() == 0) {
			initFoods();
		}
		
		if (reviewRepo.count() == 0) {
			initReviews();
		}
	}
	
	private void initFoods() throws Exception {
		FoodDto[] foods = {
			makeFoods("Chicken Pop Jumbo", 160.0,  
					"Chicks Rule", "Serving tasty & addicting chicken poppers since 2009.",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFoods("Bacsilog", 100.0,  
					"Ate Rica’s BACSILOG", "Best known for serving tasty, quality, fast, and clean superior-silog meals.",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565)
		};
		
		for (int i = 0; i < 2; i++) {
			addFoods(foods[i]);
		}
	}
	
	private void initReviews() {
		// Initialize reviews
		// automatically add users not in the database
	}
	
	private FoodDto makeFoods(
			String itemName, Double price,
			String stallName, String description,
			String locationName, Double longitude, Double latitude) {
		FoodDto foods = new FoodDto();
		foods.setItemName(itemName);
		foods.setPrice(price);
		foods.setStallName(stallName);
		foods.setDescription(description);
		foods.setLocationName(locationName);
		foods.setLongitude(longitude);
		foods.setLatitude(latitude);
		
		return foods;
	}
	
	private void addFoods(FoodDto dto) throws Exception {
		
		// Automatically add locations not in the database
		Location loc = locationRepo.findByLocationName(dto.getLocationName());
		if (loc == null) {
			loc = new Location();
			loc.setLocationName(dto.getLocationName());
			loc.setLongitude(dto.getLongitude());
			loc.setLatitude(dto.getLatitude());
			loc = locationRepo.save(loc);
		}
		
		// Automatically add food stalls not in the database
		FoodStall stall = stallRepo.findByStallName(dto.getStallName());
		if (stall == null) {
			stall = new FoodStall();
			stall.setStallName(dto.getStallName());
			stall.setDescription(dto.getDescription());
			stall.setLocationId(loc.getLocationId());
			stall = stallRepo.save(stall);
		}
		
		// Raise error when stall and location do not match
		if (stall.getLocationId() != loc.getLocationId()) {
			throw new RuntimeException(
					String.format("Locations do not match in %s", stall.getStallName()));
		}
		
		
		// Add new food item
		FoodItem item = new FoodItem();
		item.setItemName(dto.getItemName());
		item.setStallId(stall.getStallId());
		item.setPrice(dto.getPrice());
		
		item = itemRepo.save(item);
	}
}