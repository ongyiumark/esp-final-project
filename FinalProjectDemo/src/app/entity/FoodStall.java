package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="food_stall_table")
public class FoodStall {
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stallId;
	
	@Column
	@NotNull
	@Size(max=25)
	private String stallName;
	
	@Column
	@Size(max=100)
	private String description;
	
	@Column
	@NotNull
	private Long locationId;
	
	@Column
	private Long imageId;

	public Long getStallId() {
		return stallId;
	}

	public void setStallId(Long stallId) {
		this.stallId = stallId;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "FoodStall [stallId=" + stallId + ", stallName=" + stallName + ", description=" + description
				+ ", locationId=" + locationId + ", imageId=" + imageId + "]";
	}
	
}
