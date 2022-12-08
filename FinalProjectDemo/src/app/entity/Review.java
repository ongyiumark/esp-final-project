package app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity(name="review_table")
public class Review {
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewId;
	
	@Column
	@NotNull
	private Long userId;
	
	@Column
	@NotNull
	private Long stallId;
	
	@Column
	@NotNull
	@Range(min=1, max=5)
	private Integer rating;
	
	@Column
	@Size(max=300)
	private String reviewBody;
	
	@Column
	@NotNull
	private Date reviewDate;
	
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

	public void setStallId(Long itemId) {
		this.stallId = itemId;
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

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", stallId=" + stallId + ", rating=" + rating
				+ ", reviewBody=" + reviewBody + ", reviewDate=" + reviewDate + "]";
	}
	
	
}
