package app.auctions.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

public class MessageDTO {
	private long messageId;
	private UserDTO sender;
	private UserDTO receiver;
	private String text;
	private Timestamp sendDate;
	private boolean inboxStatus;	
	private boolean outboxStatus;
	public UserDTO getSender() {
		return sender;
	}
	public void setSender(UserDTO sender) {
		this.sender = sender;
	}
	public UserDTO getReceiver() {
		return receiver;
	}
	public void setReceiver(UserDTO receiver) {
		this.receiver = receiver;
	}
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
