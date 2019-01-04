package app.auctions.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Recommendation;

public class RecommendationDaoImpl implements RecommendationDao{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	@Override
	public void save(Recommendation recommendation) {
		sessionFactory.getCurrentSession().save(recommendation);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recommendation> findRecommendationsByUser(long userId) {
		return sessionFactory.getCurrentSession().createCriteria(Recommendation.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eqOrIsNull("user.userId", userId)).list();
	}

}
