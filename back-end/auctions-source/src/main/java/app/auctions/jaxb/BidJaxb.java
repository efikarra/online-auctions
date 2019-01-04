package app.auctions.jaxb;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import app.auctions.dto.UserDTO;

@XmlType( propOrder = { "bidder", "time","amount" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Bid")
public class BidJaxb {
	@XmlElement(name = "Time")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp time;

	@XmlElement(name = "Amount")
	@XmlJavaTypeAdapter(PriceAdapter.class)
	private Double amount;

	@XmlElement(name = "Bidder")
	private BidderJaxb bidder;

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

	public BidderJaxb getBidder() {
		return bidder;
	}

	public void setBidder(BidderJaxb bidder) {
		this.bidder = bidder;
	}
}
