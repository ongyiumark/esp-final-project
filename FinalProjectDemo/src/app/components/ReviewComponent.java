package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public String addNewReview(String userName, String stallName, String reviewBody, Integer rating) {
		return "This 'review/new' URL received the following information: "
				+ "\nuserName = " + userName 
				+ "\nstallName = " + stallName
				+ "\nreviewBody = " + reviewBody
				+ "\nrating = " + String.valueOf(rating);
	}
	
	public String getAllReviews() {
		return "This 'review/list' URL will return a list of all reviews";
	}
	
	public String getStallReviews(String stallName) {
		return "This 'review/stall' URL received the following information: "
				+ "\nstallName = " + stallName;
	}
	
}
