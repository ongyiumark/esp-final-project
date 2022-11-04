package app.dto;

public class ReviewSeederDto {
	private String userName;
	private String stallName;
	private Integer rating;
	private String reviewBody;
	
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
	@Override
	public String toString() {
		return "ReviewSeederDto [userName=" + userName + ", stallName=" + stallName + ", rating=" + rating
				+ ", reviewBody=" + reviewBody + "]";
	}
	
	
}
