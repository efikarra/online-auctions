package app.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.RecommendationDao;
import app.auctions.model.Recommendation;

public class RecommendationServiceImpl implements RecommendationService{
	private RecommendationDao recommendationDao;
	 
    public void setRecommendationDao(RecommendationDao recommendationDao) {
        this.recommendationDao = recommendationDao;
    }
    
	@Transactional
	@Override
	public void save(Recommendation recommendation) {
		recommendationDao.save(recommendation);
		
	}
	@Transactional
	@Override
	public List<Recommendation> findRecommendationsByUser(long userId) {
		
		return recommendationDao.findRecommendationsByUser(userId);
	}

}
