package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.repository.UserRepository;

@Component
public class UserComponent {
	
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String register(String userName, String password) {
		return "This 'user/register' URL received the following information: "
				+ "\nuserName = " + userName 
				+ "\npassword = " + password;
	}
	
	public String login(String userName, String password) {
		return "This 'user/login' URL received the following information: "
				+ "\nuserName = " + userName 
				+ "\npassword = " + password;
	}
	
	public String logout() {
		return "This 'user/logout' URL logs out the current user";
	}
	
	public String getAllUsers() {
		return "This 'user/list' URL will return a list of all users without their passwords";
	}
}
