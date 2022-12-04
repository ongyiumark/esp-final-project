package app.components;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dto.FoodSeederDto;
import app.dto.ReviewSeederDto;
import app.entity.FoodItem;
import app.entity.FoodStall;
import app.entity.Location;
import app.entity.Review;
import app.entity.User;
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
			//initFoods();
		}
		
		if (reviewRepo.count() == 0) {
			//initReviews();
		}
	}
	
	private void initFoods() throws Exception {
		FoodSeederDto[] foods = {
			makeFood("Chicken Pop Jumbo", 160.0,  
					"Chicks Rule", "Serving tasty & addicting chicken poppers since 2009.",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("Bacsilog", 100.0,  
					"Ate Rica’s BACSILOG", "Best known for serving tasty, quality, fast, and clean superior-silog meals.",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("Coffee Boomba", 40.0,  
					"Juzijuiz", "Fresh iced drinks",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("Melon Boomba", 40.0,  
					"Juzijuiz", "Fresh iced drinks",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("Red Iced Tea", 40.0,  
					"Juzijuiz", "Fresh iced drinks",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("White Chicks", 100.0,  
					"Chunky Chicken", "Chicken Sanwiches and Pasta",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("K-Chick", 110.0,  
					"Chunky Chicken", "Chicken Sanwiches and Pasta",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
			makeFood("Westside", 110.0,  
					"Chunky Chicken", "Chicken Sanwiches and Pasta",
					"Gonzaga Cafeteria", 121.07799954913702, 14.638947290686565),
		};
		
		for (int i = 0; i < 8; i++) {
			addFood(foods[i]);
		}
	}
	
	private void initReviews() {
		ReviewSeederDto[] reviews = {
				makeReview("ongyiumark", "Chunky Chicken", 5, "Affordable and delicious."),
				makeReview("ongyiumark", "Juzijuiz", 4, "Refreshing."),
				makeReview("user420", "Chunky Chicken", 4, "Large serving size")
				
		};
		
		for (int i = 0; i < 3; i++) {
			addReview(reviews[i]);
		}
	}
	
	private FoodSeederDto makeFood(
			String itemName, Double price,
			String stallName, String description,
			String locationName, Double longitude, Double latitude) {
		FoodSeederDto foods = new FoodSeederDto();
		foods.setItemName(itemName);
		foods.setPrice(price);
		foods.setStallName(stallName);
		foods.setDescription(description);
		foods.setLocationName(locationName);
		foods.setLongitude(longitude);
		foods.setLatitude(latitude);
		
		return foods;
	}
	
	private void addFood(FoodSeederDto dto) throws Exception {
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
	
	private ReviewSeederDto makeReview(
			String userName, String stallName,
			Integer rating, String reviewBody) {
		ReviewSeederDto review = new ReviewSeederDto();
		review.setUserName(userName);
		review.setStallName(stallName);
		review.setRating(rating);
		review.setReviewBody(reviewBody);
		return review;
	}
	
	private void addReview(ReviewSeederDto dto) {
		FoodStall stall = stallRepo.findByStallName(dto.getStallName());
		if (stall == null) {
			throw new RuntimeException(
					String.format("Cannot find the food stall: %s", dto.getStallName()));
		}
		
		// Automatically user not in the database
		User user = userRepo.findByUserName(dto.getUserName());
		if (user == null) {
			user = new User();
			user.setUserName(dto.getUserName());
			
			String password;
			try {
				password = SHA256Hash(dto.getUserName());
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Invalid Algorithm: " + e);
			}
			
			user.setPassword(password);
			user.setNumReviews(0);
			user = userRepo.save(user);
		}
		
		
		user.setNumReviews(user.getNumReviews()+1);
		user = userRepo.save(user);
		// Add new review
		Review review = new Review();
		review.setUserId(user.getUserId());
		review.setStallId(stall.getStallId());
		review.setRating(dto.getRating());
		review.setReviewBody(dto.getReviewBody());
		review = reviewRepo.save(review);
	}
	
	private String SHA256Hash(String input) throws NoSuchAlgorithmException {
		// Hash password
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		// Convert hash to string
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		
		// Fix string length to 64
		while(hexString.length() < 64) hexString.insert(0, '0');
		
		return hexString.toString();
	}
	
}