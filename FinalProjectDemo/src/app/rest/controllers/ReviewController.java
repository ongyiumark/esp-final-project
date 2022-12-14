package app.rest.controllers;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.ReviewComponent;
import app.dto.ReviewDto;
import app.entity.Review;

@Path("/review")
public class ReviewController {
	@Autowired
	ReviewComponent rc;
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Review addNewReview(
							@FormParam("sessionKey") String sessionKey,
							@FormParam("stallName") String stallName,
							@FormParam("reviewBody") String reviewBody,
							@FormParam("rating") Integer rating,
							@FormParam("reviewDate") String reviewDate) throws ParseException {
		return rc.addNewReview(sessionKey, stallName, reviewBody, rating, reviewDate);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReviewDto> getAllReviews() {		
		return rc.getAllReviews();
	}
	
	@GET
	@Path("/stall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReviewDto> getStallReviews(@QueryParam("stallName") String stallName) {
		return rc.getStallReviews(stallName);
	}
}
