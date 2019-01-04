package app.auctions.dao;

import java.util.List;

import app.auctions.model.Recommendation;

public interface RecommendationDao {
 void save(Recommendation recommendation);
 List<Recommendation> findRecommendationsByUser(long userId);
}
