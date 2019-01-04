package app.auctions.model;

import java.sql.Timestamp;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private long messageId;
	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private User receiver;
	@Column(name = "text", nullable = false)
	private String text;
	@Column(name = "send_date", nullable = false)
	private Timestamp sendDate;
	
	@Column(name="inbox_status",nullable=false,columnDefinition = "TINYINT(1)")
	private boolean inboxStatus;
	
	@Column(name="outbox_status",nullable=false,columnDefinition = "TINYINT(1)")
	private boolean outboxStatus;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public boolean isInboxStatus() {
		return inboxStatus;
	}

	public void setInboxStatus(boolean inboxStatus) {
		this.inboxStatus = inboxStatus;
	}

	public boolean isOutboxStatus() {
		return outboxStatus;
	}

	public void setOutboxStatus(boolean outboxStatus) {
		this.outboxStatus = outboxStatus;
	}
}
