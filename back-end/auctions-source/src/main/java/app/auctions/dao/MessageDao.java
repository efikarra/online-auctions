package app.auctions.dao;

import java.util.List;

import app.auctions.model.Message;


public interface MessageDao {
	public void save(Message message);
	public int delete(long messageId);
	public void updateInboxStatus(long messageId,boolean inboxStatus);
	public void updateOutboxStatus(long messageId,boolean outboxStatus);
	 public Message findMessageById(long messageId);
    public List<Message> findMessagesByReceiverAndInboxStatus(long userId, boolean inboxStatus);
    public List<Message> findMessagesBySenderAndOutboxStatus(long userId, boolean outputStatus);
}
