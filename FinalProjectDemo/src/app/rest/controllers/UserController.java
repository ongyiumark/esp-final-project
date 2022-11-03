package app.rest.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class UserController {
	
	@GET
	@Path("/login")
	public String login() {
		return "test";
	}
}
