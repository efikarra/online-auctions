package app.auctions.service;

import java.util.List;

import app.auctions.model.Bid;
import app.auctions.model.Item;

public interface BidService {

	public void save(Bid bid);

	int delete(long bidId);

	public List<Bid> findAllBidsByUser(long userId);

	public List<Bid> findActiveBidsByUser(long userId);

	public List<Bid> findInActiveBidsByUser(long userId);
}
