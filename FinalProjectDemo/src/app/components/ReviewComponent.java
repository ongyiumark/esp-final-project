package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.ReviewRepository;

@Component
public class ReviewComponent {
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@PostConstruct
	public void init()
	{
	}
	
	public String newReview(String userName, String stallName, String reviewBody, int rating) {
		return "This newReview URL received the following information: "
				+ "\nuserName = " + userName 
				+ "\nstallName = " + stallName
				+ "\nreviewBody = " + reviewBody
				+ "\nrating = " + String.valueOf(rating);
	}
	
	public String reviewList() {
		return "This reviewList URL will return a list of all reviews";
	}
	
	public String reviewPerStall(String stallName) {
		return "This review URL received the following information: "
				+ "\nstallName = " + stallName;
	}
	
}
