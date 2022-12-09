package app.components;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dto.ReviewDto;
import app.entity.FoodStall;
import app.entity.Review;
import app.entity.Session;
import app.entity.User;
import app.repository.FoodStallRepository;
import app.repository.ReviewRepository;
import app.repository.SessionRepository;
import app.repository.UserRepository;

@Component
public class ReviewComponent {
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FoodStallRepository stallRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@PostConstruct
	public void init() {
	}
	
	public Review addNewReview(
			String sessionKey, 
			String stallName, 
			String reviewBody, 
			Integer rating,
			String reviewDate) throws ParseException {
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException(
				String.format("Invalid session key.")
			);
		}
		
		// Find user corresponding to `userId`
		User user = userRepo.findByUserId(session.getUserId());
		if (user == null) {
			throw new RuntimeException(
				String.format("There is no user with ID '%d' in the database.", session.getUserId())
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
	
	public List<ReviewDto> getAllReviews() {
		List<Review> reviews = reviewRepo.findAll();
		List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();
		for (Review rev : reviews) {
			ReviewDto revDto = new ReviewDto();
			revDto.setRating(rev.getRating());
			revDto.setReviewBody(rev.getReviewBody());
			revDto.setStallId(rev.getStallId());
			revDto.setUserId(rev.getUserId());
			revDto.setUserName(userRepo.findByUserId(rev.getUserId()).getUserName());
			revDto.setStallName(stallRepo.findByStallId(rev.getStallId()).getStallName());
			
			reviewDtos.add(revDto);
		}
		
		return reviewDtos;
	}
	
	public List<ReviewDto> getStallReviews(String stallName) {
		// Find the food stall corresponding to `stallName`
		FoodStall stall = stallRepo.findByStallName(stallName);
		if (stall == null) {
			throw new RuntimeException(
				String.format("Food stall '%s' is not in the database.", stallName)
			);
		}
		
		List<Review> reviews = reviewRepo.findByStallId(stall.getStallId());
		List<ReviewDto> reviewDtos = new ArrayList<ReviewDto>();
		
		for (Review rev : reviews) {
			ReviewDto revDto = new ReviewDto();
			revDto.setRating(rev.getRating());
			revDto.setReviewBody(rev.getReviewBody());
			revDto.setStallId(rev.getStallId());
			revDto.setUserId(rev.getUserId());
			revDto.setUserName(userRepo.findByUserId(rev.getUserId()).getUserName());
			revDto.setStallName(stallRepo.findByStallId(rev.getStallId()).getStallName());
			
			reviewDtos.add(revDto);
		}
		return reviewDtos;
	}
	
}
