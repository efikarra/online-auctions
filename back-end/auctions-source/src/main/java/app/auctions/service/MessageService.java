package app.auctions.service;

import java.util.List;

import app.auctions.model.Message;

public interface MessageService {
	public void save(Message message);
	public int delete(long messageId);
	public void deleteFromInbox(long messageId);
	public void deleteFromOutbox(long messageId);
	public List<Message> findInboxedMessagesByReceiver(long userId);
	 public List<Message> findOutboxedMessagesBySender(long userId);
	 public Message findMessageById(long messageId);
}
