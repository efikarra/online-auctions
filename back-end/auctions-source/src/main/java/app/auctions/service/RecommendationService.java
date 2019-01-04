package app.auctions.service;

import java.util.List;

import app.auctions.model.Recommendation;

public interface RecommendationService {
	void save(Recommendation recommendation);
	 List<Recommendation> findRecommendationsByUser(long userId);
}
