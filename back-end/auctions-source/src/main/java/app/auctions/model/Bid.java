package app.auctions.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.joda.time.DateTime;

@Entity
@Table(name = "bid",uniqueConstraints = {@UniqueConstraint(columnNames = {"item_id","time"})})
public class Bid {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_id")
	private long bidId;
	
	@Column(name="time",nullable=false)
	private Timestamp time;
	
	@Column(name="amount",nullable=false)
	private double amount;
	
	@ManyToOne(fetch=FetchType.EAGER)  
    @JoinColumn(name = "item_id") 
	private Item item;
	
	@ManyToOne  
    @JoinColumn(name = "bidder_id",nullable=false) 
	private User bidder;
	
	public long getBidId() {
		return bidId;
	}
	public void setBidId(long bidId) {
		this.bidId = bidId;
	}
	public Timestamp  getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public User getBidder() {
		return bidder;
	}
	public void setBidder(User bidder) {
		this.bidder = bidder;
	}
}
