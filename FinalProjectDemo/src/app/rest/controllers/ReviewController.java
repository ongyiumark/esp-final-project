package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.ReviewComponent;

@Path("/review")
public class ReviewController {
	@Autowired
	ReviewComponent rc;
	
	@POST
	@Path("/newReview")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	public String newReview(@FormParam("userName") String userName,
							@FormParam("stallName") String stallName,
							@FormParam("reviewBody") String reviewBody,
							@FormParam("rating") int rating) {
		return rc.newReview(userName, stallName, reviewBody, rating);
	}
	
	@GET
	@Path("/reviewList")
	public String reviewList() {
		return rc.reviewList();
	}
	
	@GET
	@Path("/review")
	public String reviewPerStall(@QueryParam("stallName") String stallName) {
		return rc.reviewPerStall(stallName);
	}
}
