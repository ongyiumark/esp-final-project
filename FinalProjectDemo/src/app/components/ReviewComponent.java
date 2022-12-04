package app.components;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.FoodStall;
import app.entity.Review;
import app.entity.User;
import app.repository.FoodStallRepository;
import app.repository.ReviewRepository;
import app.repository.UserRepository;

@Component
public class ReviewComponent {
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FoodStallRepository stallRepo;
	
	@PostConstruct
	public void init() {
	}
	
	public Review addNewReview(
			String userName, 
			String stallName, 
			String reviewBody, 
			Integer rating,
			String reviewDate) throws ParseException {
		// Find user corresponding to `userName`
		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new RuntimeException(
				String.format("User '%s' is not in the database.", userName)
			);
		}
		
		// Find the food stall corresponding to `stallName`
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("Food stall '%s' is not in the database.", stallName)
			);
		}
		
		// Increment user number of reviews
		user.setNumReviews(user.getNumReviews()+1);
		user = userRepo.save(user);
		
		// Update properties
		Review review = new Review();
		review.setUserId(user.getUserId());
		review.setStallId(stall.getStallId());
		review.setReviewBody(reviewBody);
		review.setRating(rating);
		
		SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
		Date date = new Date(format.parse(reviewDate).getTime());
		review.setReviewDate(date);
		
		// Update database
		review = reviewRepo.save(review);
		return review;
	}
	
	public List<Review> getAllReviews() {
		return reviewRepo.findAll();
	}
	
	public List<Review> getStallReviews(String stallName) {
		// Find the food stall corresponding to `stallName`
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("Food stall '%s' is not in the database.", stallName)
			);
		}
		
		return reviewRepo.findByStallId(stall.getStallId());
	}
	
}
