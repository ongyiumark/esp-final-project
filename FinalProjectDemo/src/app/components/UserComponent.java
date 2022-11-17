package app.components;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.User;
import app.repository.UserRepository;

@Component
public class UserComponent {
	
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	public void init() {
		
	}
	
	private String SHA256Hash(String input) throws NoSuchAlgorithmException {
		// Hash password
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		// Convert hash to string
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		
		// Fix string length to 64
		while(hexString.length() < 64) hexString.insert(0, '0');
		
		return hexString.toString();
	}
	
	public User register(String userName, String password) {
		// Check if user already exists
		User user = userRepo.findByUserName(userName);
		if (user != null) {
			throw new RuntimeException(
				String.format("Username '%s' already exists.", userName)
			);
		}
		
		// Check if password is empty
		if (password.isEmpty()) {
			throw new RuntimeException("Please input a password");
		}
		
		// Encrypt password 
		String encryptedPassword;
		try {
			encryptedPassword = SHA256Hash(password);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Incorrect algorithm: " + e);
		}
		
		// Instantiate new user
		user = new User();
		user.setNumReviews(0);
		user.setUserName(userName);
		user.setPassword(encryptedPassword);
		
		// Update database
		user = userRepo.save(user);
		return user;
	}
	
	public String login(String userName, String password) {
		// Encrypt password
		String encryptedPassword;
		try {
			encryptedPassword = SHA256Hash(password);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Incorrect algorithm: " + e);
		}
		
		// Find user with matching credentials
		User user = userRepo.findByUserNameAndPassword(userName, encryptedPassword);
		if (user == null) {
			throw new RuntimeException("Invalid credentials");
		}
		
		return "This 'user/login' URL received the following information: "
				+ "\nuserName = " + userName 
				+ "\npassword = " + password;
	}
	
	public String logout() {
		return "This 'user/logout' URL logs out the current user";
	}
	
	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		
		// Remove passwords before returning
		for (User u : users) {
			u.setPassword(null);
		}
		return users;
	}
}
