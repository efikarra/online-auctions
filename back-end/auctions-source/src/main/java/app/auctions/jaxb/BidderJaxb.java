package app.auctions.jaxb;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = { "rating", "userId","location","country"   })
@XmlAccessorType(XmlAccessType.FIELD)
public class BidderJaxb {
	@XmlAttribute(name="UserID")
	private String userId;
	@XmlAttribute(name="Rating")
	private int rating;
	@XmlElement(name="Location")
	private String location;
	@XmlElement(name="Country")
	private String country;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
