package app.auctions.jaxb;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import app.auctions.dto.CategoryDTO;
import app.auctions.dto.LocationDTO;
@XmlType( propOrder = { "name", "categories","currently","buyPrice", "firstBid", "numberOfBids","bids","location","country","started","ends","seller","description" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "Item" )
public class ItemJaxb {
	@XmlAttribute( name = "ItemID" ,required = true)
	private String itemId;
	
	@XmlElement( name = "Name" )
	private String name;
	
	@XmlElement( name = "Currently" )
	@XmlJavaTypeAdapter(PriceAdapter.class)
	private Double currently;
	
	@XmlElement( name = "First_Bid" )
	@XmlJavaTypeAdapter(PriceAdapter.class)
	private Double firstBid;
	
	@XmlElement( name = "Number_Of_Bids" )
	private int numberOfBids;
	
	@XmlElement( name = "Started" )
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp started;
	
	@XmlElement( name = "Ends" )
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp ends;
	
	@XmlElement( name = "Description" )
	private String description;
	
	@XmlElement( name = "Buy_Price" )
	@XmlJavaTypeAdapter(PriceAdapter.class)
	private Double buyPrice;
	
	@XmlElementWrapper(name="Bids")
	@XmlElement( name = "Bid" )
	private List<BidJaxb> bids;
	
	@XmlElement( name = "Location" )
	private LocationDTO location;
	
	@XmlElement( name = "Country" )
	private String country;
	
	@XmlElement( name = "Seller" )
	private SellerJaxb seller;

	@XmlElement(name="Category")
    private Set<CategoryDTO> categories;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCurrently() {
		return currently;
	}
	public void setCurrently(Double currently) {
		this.currently = currently;
	}
	public Double getFirstBid() {
		return firstBid;
	}
	public void setFirstBid(Double firstBid) {
		this.firstBid = firstBid;
	}
	public int getNumberOfBids() {
		return numberOfBids;
	}
	public void setNumberOfBids(int numberOfBids) {
		this.numberOfBids = numberOfBids;
	}
	public Timestamp getStarted() {
		return started;
	}
	public void setStarted(Timestamp started) {
		this.started = started;
	}
	public Timestamp getEnds() {
		return ends;
	}
	public void setEnds(Timestamp ends) {
		this.ends = ends;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public List<BidJaxb> getBids() {
		return bids;
	}
	public void setBids(List<BidJaxb> bids) {
		this.bids = bids;
	}
	public SellerJaxb getSeller() {
		return seller;
	}
	public void setSeller(SellerJaxb seller) {
		this.seller = seller;
	}
	public Set<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryDTO> categories) {
		this.categories = categories;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
}
