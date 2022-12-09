package app.dto;

public class ReviewDto {
	private Integer rating;
	private String reviewBody;
	private String userName;
	private String stallName;
	private String errorMessage;

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
		return "ReviewDto [rating=" + rating + ", reviewBody=" + reviewBody + ", userName=" + userName + ", stallName="
				+ stallName + ", errorMessage=" + errorMessage + "]";
	}

}
