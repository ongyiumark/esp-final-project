package app.components;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Session;
import app.entity.User;
import app.repository.SessionRepository;
import app.repository.UserRepository;

@Component
public class UserComponent {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SessionRepository sessionRepo;
	
	@PostConstruct
	public void init() {
		// Delete all sessions at start up
		sessionRepo.deleteAll();
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
		
		user.setPassword("~redacted~");
		user.setUserId(null);
		return user;
	}
	
	public User update(
			String sessionKey, 
			String currentPassword, 
			String newUserName, 
			String newPassword) {
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException(
				String.format("Invalid session key.")
			);
		}
		
		// Check if user already exists
		User user = userRepo.findByUserName(newUserName);
		if (user != null) {
			throw new RuntimeException(
				String.format("Username '%s' already exists.", newUserName)
			);
		}
		
		// Check if password is empty
		if (newPassword.isEmpty()) {
			throw new RuntimeException("Please input a password");
		}
		
		// Encrypt password 
		String encryptedPassword;
		try {
			encryptedPassword = SHA256Hash(newPassword);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Incorrect algorithm: " + e);
		}
		
		String oldEncryptedPassword;
		try {
			oldEncryptedPassword = SHA256Hash(currentPassword);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Incorrect algorithm: " + e);
		}
		
		User currentUser = userRepo.findByUserId(session.getUserId());
		user = userRepo.findByUserNameAndPassword(currentUser.getUserName(), oldEncryptedPassword);
		if (user == null) {
			throw new RuntimeException("Invalid credentials.");
		}
		user.setNumReviews(0);
		user.setUserName(newUserName);
		user.setPassword(encryptedPassword);
		
		// Update database
		user = userRepo.save(user);
		user.setPassword("~redacted~");
		user.setUserId(null);
		return user;
	}
	
	public Session login(String userName, String password) {
		if (sessionRepo.count() > 0) {
			throw new RuntimeException("A user is already logged in.");
		}
		
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
		
		// Instantiate new session
		Session session = new Session();
		String sessionKey = UUID.randomUUID().toString();
		session.setUserId(user.getUserId());
		session.setSessionKey(sessionKey);
		
		// Save session
		session = sessionRepo.save(session);
		
		return session;
	}
	
	public String logout(String sessionKey) {
		// Find session with corresponding `sessionKey`
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException("Invalid session key.");
		}
		
		// Delete session from table
		sessionRepo.deleteById(session.getSessionId());
		return "Logged out successfully.";
	}
	
	public User compareKey(String sessionKey) {
		Session session = sessionRepo.findBySessionKey(sessionKey);
		if (session == null) {
			throw new RuntimeException("Invalid session key.");
		}
		
		User user = userRepo.findByUserId(session.getUserId());
		user.setPassword("~redacted~");
		user.setUserId(null);
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		
		// Remove passwords before returning
		for (User u : users) {
			u.setPassword("~redacted~");
			u.setUserId(null);
		}
		return users;
	}
}
