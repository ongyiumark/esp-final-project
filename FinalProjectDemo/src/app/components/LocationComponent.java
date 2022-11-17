package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Location;
import app.repository.LocationRepository;

@Component
public class LocationComponent {
	
	@Autowired
	LocationRepository locationRepo;
	
	@PostConstruct
	public void init() {
	}
	
	public Location addNewLocation(String locationName, Double longitude, Double latitude) {
		// Instantiate new location if it is not in the database
		Location loc = locationRepo.findByLocationName(locationName);
		if (loc == null) loc = new Location();
		
		// Update properties
		loc.setLocationName(locationName);
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);

		// Update database
		loc = locationRepo.save(loc);
		return loc;
	}
	
	public List<Location> getAllLocations() {
		return locationRepo.findAll();
	}
	
}
