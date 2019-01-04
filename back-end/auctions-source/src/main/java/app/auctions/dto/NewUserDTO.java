package app.auctions.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import app.auctions.model.Role;

public class NewUserDTO {
	@NotEmpty
	@NotNull
	@Size(max=50, message="Your name should be at most 50 characters.")
	private String username;
	
	@NotEmpty
	@NotNull
	@Size(max=50, message="Your name should be at most 100 characters.")
	private String password;
	
	@NotEmpty
	@NotNull
	@Size(max=100, message="Your name should be at most 100 characters.")
	private String firstname;	
	
	@NotEmpty
	@NotNull
	@Size(max=100, message="Your lastname should be at most 100 characters.")
	private String lastname;	
	
	@NotEmpty
	@NotNull
	@Size(max=80, message="Your vat number should be at most 100 characters.")
	private String vatNumber;
	
	@NotEmpty
	@NotNull
	@Email
	@Size(max=80, message="Your email should be at most 80 characters.")
	private String email;	
	

	@Size(max=80, message="Your phone number should be at most 30 characters.")
	private String phoneNumber;
	
	@NotEmpty
	@NotNull
	@Size(max=80, message="Your country should be at most 100 characters.")
	private String country;
	
	@NotEmpty
	@NotNull
	@Size(max=80, message="Your location should be at most 100 characters.")
	private String location;
	
	@NotEmpty
	@NotNull
	@Size(max=80, message="Your street should be at most 100 characters.")
	private String street;
	
	@NotNull
	private int streetNumber;
	
	@NotNull
	private int postCode;
	
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
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
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
	
}
