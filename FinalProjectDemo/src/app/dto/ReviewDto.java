package app.dto;

public class ReviewDto {
	private Long userId;
	private Long stallId;
	private Integer rating;
	private String reviewBody;
	private String userName;
	private String stallName;
	private String errorMessage;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStallId() {
		return stallId;
	}

	public void setStallId(Long stallId) {
		this.stallId = stallId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReviewBody() {
		return reviewBody;
	}

	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	@Override
	public String toString() {
		return "ReviewDto [userId=" + userId + ", stallId=" + stallId + ", rating=" + rating + ", reviewBody="
				+ reviewBody + ", userName=" + userName + ", stallName=" + stallName + ", errorMessage=" + errorMessage
				+ "]";
	}

	

	
}
