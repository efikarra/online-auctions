package app.auctions.dao;

import java.sql.Timestamp;
import java.util.List;

import app.auctions.model.Bid;
import app.auctions.model.User;

public interface BidDao {
	public void save(Bid bid);
	 int delete(long bidId);
	 public List<Bid> findAllBidsByUser(long userId);
	 public List<Bid> findBidsByUserAfterItemEndDate(long userId, Timestamp endDate);
	 public List<Bid> findBidsByUserBeforeItemEndDate(long userId, Timestamp endDate);
}
