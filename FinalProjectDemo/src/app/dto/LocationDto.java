package app.dto;

public class LocationDto {
	private String locationName;
	private Double latitude;
	private Double longitude;
	private String errorMessage;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "LocationDto [locationName=" + locationName + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", errorMessage=" + errorMessage + "]";
	}
	
	

	
}
