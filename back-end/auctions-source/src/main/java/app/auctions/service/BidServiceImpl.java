package app.auctions.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.BidDao;
import app.auctions.dao.CategoryDao;
import app.auctions.model.Bid;

public class BidServiceImpl implements BidService {

	private BidDao bidDao;

	public void setBidDao(BidDao bidDao) {
		this.bidDao = bidDao;
	}

	@Transactional
	@Override
	public void save(Bid bid) {
		bidDao.save(bid);

	}

	@Transactional
	@Override
	public int delete(long bidId) {
		return bidDao.delete(bidId);
	}

	@Transactional
	@Override
	public List<Bid> findAllBidsByUser(long userId) {
		return bidDao.findAllBidsByUser(userId);
	}

	@Transactional
	@Override
	public List<Bid> findActiveBidsByUser(long userId) {
		Date now=new Date();
		return bidDao.findBidsByUserAfterItemEndDate(userId, new Timestamp(now.getTime()));
	}

	@Transactional
	@Override
	public List<Bid> findInActiveBidsByUser(long userId) {
		Date now=new Date();
		return bidDao.findBidsByUserBeforeItemEndDate(userId, new Timestamp(now.getTime()));
	}

}
