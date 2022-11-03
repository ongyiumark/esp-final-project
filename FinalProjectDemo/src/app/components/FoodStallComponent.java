package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public String stallList() {
		return "This stallList URL will return a list of all foodstalls";
	}
	
	
	public String addNewFoodStall(
			String stallName,
			String description, 
			String locationName, 
			Double longitude, 
			Double latitude) {

		return "This addNewFoodStall URL will receive the following information:"  
			+ "\nstallName = " + stallName
			+ "\ndescription = " + description
			+ "\nlocationName = " + locationName
			+ "\nlongitude = " + longitude
			+ "\nlatitude = " + latitude;
	}
}