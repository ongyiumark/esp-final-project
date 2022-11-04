package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserNameAndPassword(String userName, String password);
	public User findByUserName(String userName);
	public User findByUserId(Long userId);
}
