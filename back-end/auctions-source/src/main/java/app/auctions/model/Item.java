package app.auctions.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long itemId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "currently", nullable = false)
	private double currently;

	@Column(name = "first_bid", nullable = false)
	private double firstBid;

	@Column(name = "number_of_bids", nullable = false)
	private int numberOfBids;

	@Column(name = "started", nullable = false)
	private Timestamp started;

	@Column(name = "ends", nullable = false)
	private Timestamp ends;

	@Column(name = "description")
	private String description;

	@Column(name = "buy_price", nullable = false)
	private double buyPrice;

	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	private Set<Photo> photos;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Bid> bids;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id", nullable = false)
	private User seller;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "item_category", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	private Set<Category> categories;
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

	public double getCurrently() {
		return currently;
	}

	public void setCurrently(double currently) {
		this.currently = currently;
	}

	public double getFirstBid() {
		return firstBid;
	}

	public void setFirstBid(double firstBid) {
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

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return seller;
	}

	public void setUser(User user) {
		this.seller = user;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
}
