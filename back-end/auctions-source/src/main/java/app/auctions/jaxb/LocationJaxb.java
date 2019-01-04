package app.auctions.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import app.auctions.dto.LocationDTO;
@XmlType( propOrder = { "location","country" })
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationJaxb {
	
	@XmlElement(name="Location")
	private LocationDTO location;
	@XmlElement(name="Country")
	private String country;
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
