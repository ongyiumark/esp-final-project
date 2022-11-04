package app.dto;

public class ReviewDto {
	private Long userId;
	private Long stallId;
	private Integer rating;
	private String reviewBody;
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

	@Override
	public String toString() {
		return "ReviewDto [userId=" + userId + ", stallId=" + stallId + ", rating=" + rating + ", reviewBody="
				+ reviewBody + ", errorMessage=" + errorMessage + "]";
	}

	
}
