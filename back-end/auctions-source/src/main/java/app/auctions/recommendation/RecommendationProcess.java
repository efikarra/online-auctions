package app.auctions.recommendation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.model.Bid;
import app.auctions.model.Item;
import app.auctions.model.Recommendation;
import app.auctions.model.User;
import app.auctions.service.ItemService;
import app.auctions.service.RecommendationService;

public class RecommendationProcess {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");

		ItemService is = (ItemService) context.getBean("itemService");
		RecommendationService rs = (RecommendationService) context.getBean("recommendationService");
		HibernateMapperFactory mapper = (HibernateMapperFactory) context
				.getBean("hibernateMapperFactory");
        List<Item> items=is.listItems();
		System.out.println(items.size());
		Map<String, List<Item>> mapUserItems = new HashMap<String, List<Item>>();
		Map<String, Long> mapUserIds = new HashMap<String, Long>();
		Map<Long, Item> mapItemIds = new HashMap<Long, Item>();
		Map<String, User> mapUsersUsernames = new HashMap<String, User>();
		long id = 0;
		for (int i = 0; i < items.size(); i++) {
			mapItemIds.put(items.get(i).getItemId(), items.get(i));
			Set<Bid> bids = items.get(i).getBids();
			for (Bid bid: bids) {
				if (bid.getBidder() != null) {
					String username = bid.getBidder().getUsername();
					if (mapUserItems.containsKey(username)) {
						List<Item> its = mapUserItems.get(username);
						its.add(items.get(i));
						mapUserItems.put(bid.getBidder().getUsername(),
								its);
					} else {
						List<Item> its = new ArrayList<Item>();
						its.add(items.get(i));
						mapUserItems.put(bid.getBidder().getUsername(),
								its);
						mapUserIds.put(username, id);
						mapUsersUsernames.put(username,bid.getBidder());
						id++;
						//System.out.println(i);
					}
					
				}
			}

		}

		FastByIDMap<FastIDSet> userData = new FastByIDMap<FastIDSet>();
		for (Map.Entry<String, List<Item>> e : mapUserItems.entrySet()) {
			FastIDSet set = new FastIDSet(e.getValue().size());
			//System.out.println(e.getValue().size());
			for (int i = 0; i < e.getValue().size(); i++) {
				//System.out.println(Long.valueOf(e.getValue().get(i).getItemId()));
				set.add(Long.valueOf(e.getValue().get(i).getItemId()));
			}
			userData.put(mapUserIds.get(e.getKey()), set);

		}
		DataModel model = new GenericBooleanPrefDataModel(userData);
		UserSimilarity userSimilarity = null;
		try {
			userSimilarity = new TanimotoCoefficientSimilarity (model);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(140,
					userSimilarity, model);
			Recommender recommender = new GenericUserBasedRecommender(model,
					neighborhood, userSimilarity);
			
			int c=0;
			for (Map.Entry<String, Long> e : mapUserIds.entrySet()) {
			List<RecommendedItem> recommendations = recommender
					.recommend(e.getValue(), 10);
			if(!recommendations.isEmpty()){
				c++;
			for (RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);
				Recommendation r=new Recommendation();
				Item it=mapItemIds.get(recommendation.getItemID());
				r.setItem(it);
				User u=mapUsersUsernames.get(e.getKey());
				r.setUser(u);
				rs.save(r);
			}}
			
			}
			System.out.println(c);
		} catch (TasteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		  }
		 
	}

