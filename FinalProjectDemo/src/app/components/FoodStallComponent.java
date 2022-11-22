package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.FoodStall;
import app.entity.Location;
import app.repository.FoodStallRepository;
import app.repository.LocationRepository;

@Component
public class FoodStallComponent {
	@Autowired
	FoodStallRepository stallRepo; 
	
	@Autowired
	LocationRepository locationRepo;  
	

	@PostConstruct
	public void init() {
		
	}
	
	public List<FoodStall> getAllStalls() {
		return stallRepo.findAll();
	}
	
	
	public FoodStall addNewFoodStall(
			String stallName,
			String description, 
			String locationName, 
			Double longitude, 
			Double latitude) {
		
		// Find the location corresponding to `locationName`
		Location loc = locationRepo.findByLocationName(locationName);
		if (loc == null) {
			// Instantiate new location
			loc = new Location();
			loc.setLatitude(latitude);
			loc.setLongitude(longitude);
			loc.setLocationName(locationName);
			
			// Update database
			loc = locationRepo.save(loc);
		}
		
		// Check if food stall already exists in the same location
		FoodStall stall = stallRepo.findByStallNameAndLocationId(stallName, 
				loc.getLocationId());
		
		// Instantiate new food stall if it is not in the database
		if (stall == null) stall = new FoodStall();
		
		// Update properties
		stall.setDescription(description);
		stall.setLocationId(loc.getLocationId());
		stall.setStallName(stallName);

		// Update database
		stall = stallRepo.save(stall);
		return stall;
	}
}
