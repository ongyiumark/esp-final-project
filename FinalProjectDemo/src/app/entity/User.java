package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="user_table")
public class User {
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column
	@NotNull
	@Size(max=16)
	private String userName;
	
	@Column
	@NotNull
	@Size(max=64)
	private String password;
	
	@Column
	@NotNull
	@Min(value=0L)
	private Integer numReviews;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", numReviews="
				+ numReviews + "]";
	}

	
}
