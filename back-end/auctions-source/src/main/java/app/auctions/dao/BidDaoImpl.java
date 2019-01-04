package app.auctions.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Bid;
import app.auctions.model.Category;
import app.auctions.model.Item;

public class BidDaoImpl implements BidDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void save(Bid bid) {
		sessionFactory.getCurrentSession().saveOrUpdate(bid);
		
	}

	@Override
	public int delete(long bidId) {
		String hql = "delete from Bid where bidId= :bidId";
		int c = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("bidId", bidId).executeUpdate();
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findAllBidsByUser(long userId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Bid.class).
						add(Restrictions.eq("bidder.userId", userId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findBidsByUserAfterItemEndDate(long userId, Timestamp endDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Bid.class).createAlias("item", "item").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).
                add(Restrictions.and(Restrictions.eq("bidder.userId", userId),Restrictions.gt("item.ends", endDate))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findBidsByUserBeforeItemEndDate(long userId, Timestamp endDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Bid.class).createAlias("item", "item").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).
                add(Restrictions.and(Restrictions.eq("bidder.userId", userId),Restrictions.lt("item.ends", endDate))).list();
	}
}
