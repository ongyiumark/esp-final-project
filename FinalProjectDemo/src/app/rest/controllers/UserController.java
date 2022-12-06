package app.rest.controllers;

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

import app.components.UserComponent;
import app.entity.Session;
import app.entity.User;

@Path("/user")
public class UserController {
	@Autowired
	UserComponent uc;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User register(
			@FormParam("userName") String userName, 
			@FormParam("password") String password) {
		return uc.register(userName, password);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Session login(
			@FormParam("userName") String userName, 
			@FormParam("password") String password) {
		return uc.login(userName, password);
	}
	
	@GET
	@Path("/logout")
	public String logout(@QueryParam("sessionKey") String sessionKey) {
		return uc.logout(sessionKey);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return uc.getAllUsers();
	}
	
	@GET
	@Path("/session")
	@Produces(MediaType.APPLICATION_JSON)
	public User compareKey(@QueryParam("sessionKey") String sessionKey) {
		return uc.compareKey(sessionKey);
	}
	
}
