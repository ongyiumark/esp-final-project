package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity(name="location_table")
public class Location {
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long locationId;
	
	@Column
	@NotNull
	@Size(max=30)
	private String locationName;
	
	@Column
	@NotNull
	@Range(min=-180, max=180)
	private Double longitude;
	
	@Column
	@NotNull
	@Range(min=-90, max=90)
	private Double latitude;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
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
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
	
	
}
