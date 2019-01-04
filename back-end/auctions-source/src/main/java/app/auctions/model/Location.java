package app.auctions.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "location")
public class Location {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="location_id")
	private long locationId;
	
	@Column(name="location_name",nullable=false)
	private String locationName;
	
	@Column(name="country",nullable=false)
	private String country;

	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;
	
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
}
