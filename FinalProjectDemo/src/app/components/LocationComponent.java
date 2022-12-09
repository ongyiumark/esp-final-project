package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Location;
import app.entity.Session;
import app.repository.LocationRepository;
import app.repository.SessionRepository;

@Component
public class LocationComponent {
	
	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@PostConstruct
	public void init() {
	}
	
	public Location addNewLocation(
			String sessionKey,
			String locationName, 
			Double longitude, 
			Double latitude) {
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException(
				String.format("Invalid session key.")
			);
		}
		
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
