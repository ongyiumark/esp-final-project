package app.components;

public class ReviewDto {
	private Long reviewId;
	private Long userId;
	private Long stallId;
	private Integer rating;
	private String reviewBody;

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

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

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", stallId=" + stallId + ", rating=" + rating
				+ ", reviewBody=" + reviewBody + "]";
	}
}
