package app.auctions.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class ItemDTO {
	
	private long itemId;
	private String name;
	private Double currently;
	private Double firstBid;
	private int numberOfBids;
	private Timestamp started;
	private Timestamp ends;
	private String description;
	private Double buyPrice;
	private Set<BidDTO> bids;
	private LocationDTO location;
	private UserDTO seller;
    private Set<CategoryDTO> categories;
	private Set<PhotoDTO> photos;
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
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
	public Set<BidDTO> getBids() {
		return bids;
	}
	public void setBids(Set<BidDTO> bids) {
		this.bids = bids;
	}
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	public UserDTO getSeller() {
		return seller;
	}
	public void setSeller(UserDTO seller) {
		this.seller = seller;
	}
	public Set<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryDTO> categories) {
		this.categories = categories;
	}
	public Set<PhotoDTO> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<PhotoDTO> photos) {
		this.photos = photos;
	}
}
