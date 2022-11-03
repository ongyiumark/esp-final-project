package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.UserComponent;

@Path("/user")
public class UserController {
	@Autowired
	UserComponent uc;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String register(
			@FormParam("userName") String userName, 
			@FormParam("password") String password) {
		return uc.register(userName, password);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String login(
			@FormParam("userName") String userName, 
			@FormParam("password") String password) {
		return uc.login(userName, password);
	}
	
	@GET
	@Path("/logout")
	public String logout() {
		return uc.logout();
	}
	
}
