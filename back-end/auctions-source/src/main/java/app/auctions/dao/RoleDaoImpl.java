package app.auctions.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Category;
import app.auctions.model.Role;

public class RoleDaoImpl implements RoleDao{

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Role findRoleByName(String roleName) {
		return (Role) sessionFactory.getCurrentSession()
                .createCriteria(Role.class).add(Restrictions.eq("roleName",roleName)).uniqueResult();
	}

}
