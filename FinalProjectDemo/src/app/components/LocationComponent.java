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
	
	public String addNewLocation(String locationName, Double longitude, Double latitude) {
		return "This 'location/new' URL received the following information: "
				+ "\nlocationName = " + locationName 
				+ "\nlongitude = " + String.valueOf(longitude)
				+ "\nlatitude = " + String.valueOf(latitude);
	}
	
	public String getAllLocations() {
		return "This 'location/list' URL will return a list of all locations";
	}
	
}
