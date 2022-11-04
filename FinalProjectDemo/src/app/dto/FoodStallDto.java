package app.dto;

public class FoodStallDto {
	private String stallName;
	private String description;
	private Long locationId;
	private String errorMessage;
	
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "FoodStallDto [stallName=" + stallName + ", description=" + description + ", locationId=" + locationId
				+ ", errorMessage=" + errorMessage + "]";
	}
	
	
}
