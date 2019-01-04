package app.auctions.model;

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
@Table(name = "user")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;
	
	@Column(name="username",nullable=false,unique=true)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="firstname",nullable=false)
	private String firstname;
	
	@Column(name="lastname",nullable=false)
	private String lastname;
	
	@Column(name="vat_number",nullable=false,unique=true)
	private String vatNumber;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="phone_number",nullable=false,unique=true)
	private String phoneNumber;
	
	@Column(name="country",nullable=false)
	private String country;
	
	@Column(name="location",nullable=false)
	private String location;
	
	@Column(name="street",nullable=false)
	private String street;
	
	@Column(name="street_number",nullable=false)
	private int streetNumber;
	
	@Column(name="post_code",nullable=false)
	private int postCode;
	
	@Column(name="bidder_rating",nullable=false)
	private int bidderRating;
	
	@Column(name="seller_rating",nullable=false)
	private int sellerRating;
	
	@OneToMany(mappedBy = "bidder",fetch=FetchType.LAZY,cascade = CascadeType.ALL)  
	private Set<Bid> bids;
	
	@OneToMany(mappedBy = "seller",fetch=FetchType.LAZY,cascade = CascadeType.ALL)  
	private Set<Item> items;
	
	@Column(name="enabled",nullable=false,columnDefinition = "TINYINT(1)")
	private boolean enabled;
	
	@ManyToOne  
    @JoinColumn(name = "role_id",nullable=false) 
	    private Role role;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNamber) {
		this.vatNumber = vatNamber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public int getBidderRating() {
		return bidderRating;
	}
	public void setBidderRating(int bidderRating) {
		this.bidderRating = bidderRating;
	}
	public int getSellerRating() {
		return sellerRating;
	}
	public void setSellerRating(int sellerRating) {
		this.sellerRating = sellerRating;
	}
	public Set<Bid> getBids() {
		return bids;
	}
	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
