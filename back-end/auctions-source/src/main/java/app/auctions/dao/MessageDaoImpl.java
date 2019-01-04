package app.auctions.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Message;

public class MessageDaoImpl implements MessageDao{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessagesByReceiverAndInboxStatus(long userId, boolean inboxStatus) {
		return (List<Message>) sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.and(Restrictions.eq("inboxStatus", inboxStatus),Restrictions.eq("receiver.userId", userId))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessagesBySenderAndOutboxStatus(long userId, boolean outputStatus) {
		return (List<Message>) sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.and(Restrictions.eq("outboxStatus", outputStatus),Restrictions.eq("sender.userId", userId))).list();
	}

	@Override
	public void save(Message message) {
		sessionFactory.getCurrentSession().saveOrUpdate(message);
		
	}

	@Override
	public int delete(long messageId) {
		String hql="delete from Message where messageId= :messageId";
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("messageId", messageId).executeUpdate();
	}

	@Override
	public Message findMessageById(long messageId) {
		return (Message) sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.idEq( messageId)).uniqueResult();
	}

	@Override
	public void updateInboxStatus(long messageId, boolean inboxStatus) {
		String hql="update Message set inboxStatus= :inboxStatus where messageId= :messageId";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter("inboxStatus", inboxStatus).setParameter("messageId", messageId).executeUpdate();

	}

	@Override
	public void updateOutboxStatus(long messageId, boolean outboxStatus) {
		String hql="update Message set outboxStatus= :outboxStatus where messageId= :messageId";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter("outboxStatus", outboxStatus).setParameter("messageId", messageId).executeUpdate();

	}
}
