package app.auctions.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Category;
import app.auctions.model.Item;
import app.auctions.model.Message;
import app.auctions.model.User;

public class UserDaoImpl implements UserDao{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
    @SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		return sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
	}
	@Override
	public User findUserByUsername(String username) {
		return (User) sessionFactory.getCurrentSession()
                .createCriteria(User.class).add(Restrictions.eq("username",username)).uniqueResult();
	}
	@Override
	public boolean isUsernameUnique(String username) {
	User user= (User) sessionFactory.getCurrentSession()
	                .createCriteria(User.class).add(Restrictions.eq("username",username)).uniqueResult();
	if(user==null)
	{
		return true;
	}
	else
	{
		return false;
	}
	              
	}
	@Override
	public User findUserWithRolesByUsername(String username) {
		User user=(User) sessionFactory.getCurrentSession()
        .createCriteria(User.class).add(Restrictions.eq("username",username)).uniqueResult();
		if(user!=null)
		{ Hibernate.initialize(user.getRole()); }
		return user;
	}
	@Override
	public User findUserById(long userId) {
		return (User) sessionFactory.getCurrentSession()
		        .createCriteria(User.class).add(Restrictions.idEq(userId)).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByStatus(boolean enabled) {
		return (List<User>) sessionFactory.getCurrentSession()
		        .createCriteria(User.class).add(Restrictions.eq("enabled",enabled)).list();
	}
	@Override
	public User findUserByStatusAndUsername(boolean enabled, String username) {
		return (User) sessionFactory.getCurrentSession()
                .createCriteria(User.class).add(Restrictions.and(Restrictions.eq("enabled", enabled),Restrictions.eq("username",username))).uniqueResult();
	}
	

}
