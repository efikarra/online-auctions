package app.auctions.dto;

import java.sql.Timestamp;




import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import app.auctions.jaxb.PriceAdapter;
import app.auctions.jaxb.TimestampAdapter;
import app.auctions.model.Item;

public class BidDTO {
	private long bidId;
	private Timestamp time;
	private Double amount;
	private UserDTO bidder;
	private ItemDTO item;
	
	public long getBidId() {
		return bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public UserDTO getBidder() {
		return bidder;
	}

	public void setBidder(UserDTO bidder) {
		this.bidder = bidder;
	}

	public ItemDTO getItem() {
		return item;
	}

	public void setItem(ItemDTO item) {
		this.item = item;
	}
}
