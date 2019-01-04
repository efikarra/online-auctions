package app.auctions.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Category;
import app.auctions.model.Item;

public class CategoryDaoImpl implements CategoryDao{

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void save(Category category) {
		sessionFactory.getCurrentSession().save(category);
		
	}
    @SuppressWarnings("unchecked")
	@Override
	public List<Category> listCategories() {
		return sessionFactory.getCurrentSession()
                .createCriteria(Category.class).addOrder(Order.asc("categoryName")).list();
	}
	@Override
	public Category findCategoryByName(String categoryName) {
	 return (Category) sessionFactory.getCurrentSession()
                .createCriteria(Category.class).add(Restrictions.eq("categoryName",categoryName)).uniqueResult();
	}

}
