package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.LocationRepository;

@Component
public class LocationComponent {
	
	@Autowired
	LocationRepository locationRepo;
	
	@PostConstruct
	public void init() {
	}
	
	public String newLocation(String locationName, Double longitude, Double latitude) {
		return "This newLocation URL received the following information: "
				+ "\nlocationName = " + locationName 
				+ "\nlongitude = " + String.valueOf(longitude)
				+ "\nlatitude = " + String.valueOf(latitude);
	}
	
	public String locationList() {
		return "This locationList URL will return a list of all locations";
	}
	
}
