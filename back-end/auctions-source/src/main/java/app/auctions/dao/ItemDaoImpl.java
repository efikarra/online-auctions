package app.auctions.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Bid;
import app.auctions.model.Item;

public class ItemDaoImpl implements ItemDao{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void save(Item item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<Item> listItems() {
		return sessionFactory.getCurrentSession()
                .createCriteria(Item.class).setFetchMode("bids", FetchMode.JOIN).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public Item findItemById(long itemId) {
		return (Item) sessionFactory.getCurrentSession().createCriteria(Item.class).add(Restrictions.eq("itemId", itemId)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findItemBids(long itemId) {
		return (List<Bid>)sessionFactory.getCurrentSession()
				.createCriteria(Bid.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).
				add(Restrictions.eq("item.itemId", itemId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAllItemsByUser(long userId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Item.class).
						add(Restrictions.eq("seller.userId", userId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> itemsBeforeStartAndAfterEndByUser(long userId, Timestamp startDate, Timestamp endDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.and(Restrictions.eq("seller.userId", userId),Restrictions.and(Restrictions.lt("started", startDate),Restrictions.gt("ends", endDate)))).list();
	}

	@Override
	public int delete(long itemId) {
		String hql = "delete from Item where itemId= :itemId";
		int c = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("itemId", itemId).executeUpdate();
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> search(String keyword, String categoryName, Double minPrice,
			Double maxPrice, String locationName, Timestamp ends, Timestamp started) {
		Criteria c=sessionFactory
				.getCurrentSession()
				.createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.createAlias("categories", "categories")
				.createAlias("location", "location").add(Restrictions.and(Restrictions.gt("ends", ends),Restrictions.lt("started", started)));
		if(keyword!=null)
		{
			c.add(Restrictions.or(Restrictions.ilike("name", keyword,
					MatchMode.ANYWHERE),
							Restrictions.ilike("description", keyword,
									MatchMode.ANYWHERE)));
		}
		if(categoryName!=null)
		{
			c.add(Restrictions.eq("categories.categoryName",categoryName));
		}
		if(locationName!=null)
		{
			c.add(Restrictions.or(Restrictions.ilike("location.locationName", locationName,
					MatchMode.ANYWHERE),
					Restrictions.ilike("location.country", locationName,
							MatchMode.ANYWHERE)));
		}
		if(minPrice!=null)
		{
			c.add(Restrictions.ge("buyPrice",minPrice));
		}
		if(maxPrice!=null)
		{
			c.add(Restrictions.le("buyPrice",maxPrice));
		}
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> itemsBeforeStartAndAfterEnd(Timestamp startDate, Timestamp endDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.and(Restrictions.lt("started", startDate),Restrictions.gt("ends", endDate))).list();
	}

	@Override
	public Item findItemByIdWithBids(long itemId) {
		Item item=(Item) sessionFactory.getCurrentSession().createCriteria(Item.class).add(Restrictions.eq("itemId", itemId)).uniqueResult();
		if(item!=null)
		{ Hibernate.initialize(item.getBids()); }
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findItemsAfterStartDateByUser(long userId,
			Timestamp startDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).
                add(Restrictions.and(Restrictions.eq("seller.userId", userId),Restrictions.gt("started", startDate))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findItemsBeforeEndDateByUser(long userId, Timestamp endDate) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Item.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).
                add(Restrictions.and(Restrictions.eq("seller.userId", userId),Restrictions.lt("ends", endDate))).list();
	}

}
