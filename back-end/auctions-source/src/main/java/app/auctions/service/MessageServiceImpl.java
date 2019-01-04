package app.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.MessageDao;
import app.auctions.model.Message;

public class MessageServiceImpl implements MessageService{
	private MessageDao messageDao;
	 
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    @Transactional
	@Override
	public List<Message> findInboxedMessagesByReceiver(long userId) {
		return messageDao.findMessagesByReceiverAndInboxStatus(userId, true);
	}
    @Transactional
	@Override
	public List<Message> findOutboxedMessagesBySender(long userId) {
		return messageDao.findMessagesBySenderAndOutboxStatus(userId, true);
	}
    @Transactional
	@Override
	public void save(Message message) {
    	messageDao.save(message);
		
	}
    @Transactional
	@Override
	public int delete(long messageId) {
    	return messageDao.delete(messageId);
		
	}
    @Transactional
	@Override
	public Message findMessageById(long messageId) {
		return messageDao.findMessageById(messageId);
	}
    @Transactional
	@Override
	public void deleteFromInbox(long messageId) {
    	messageDao.updateInboxStatus(messageId, false);
	}
    @Transactional
	@Override
	public void deleteFromOutbox(long messageId) {
    	messageDao.updateOutboxStatus(messageId, false);
	}

}
