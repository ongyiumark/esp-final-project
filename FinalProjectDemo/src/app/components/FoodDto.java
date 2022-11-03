package app.components;

public class FoodDto {
	private String itemName;
	private Double price;
	
	private String stallName;
	private String description;

	private String locationName;
	private Double longitude;
	private Double latitude;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "FoodDto [itemName=" + itemName + ", price=" + price + ", stallName=" + stallName + ", description="
				+ description + ", locationName=" + locationName + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
	
	
	
	
}
