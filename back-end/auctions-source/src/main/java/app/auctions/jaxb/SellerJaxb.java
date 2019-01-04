package app.auctions.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlType( propOrder = { "rating", "userId"  })
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerJaxb {
	@XmlAttribute(name="UserID")
	private String userId;
	@XmlAttribute(name="Rating")
	private int rating;
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
}
