package app.auctions.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.ItemDao;
import app.auctions.model.Bid;
import app.auctions.model.Item;

public class ItemServiceImpl implements ItemService{
	
	private ItemDao itemDao;
	 
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    
	@Transactional
	@Override
	public void save(Item item) {
		itemDao.save(item);
		
	}

	@Transactional
	@Override
	public List<Item> listItems() {
		return itemDao.listItems();
	}
	@Transactional
	@Override
	public Item findItemById(long itemId) {
		return itemDao.findItemById(itemId);
	}
	@Transactional
	@Override
	public List<Bid> findItemBids(long itemId) {
		return itemDao.findItemBids(itemId);
	}
	@Transactional
	@Override
	public List<Item> findAllItemsByUser(long userId) {
		return itemDao.findAllItemsByUser(userId);
	}
	@Transactional
	@Override
	public List<Item> findActiveItemsByUser(long userId) {
		Date now=new Date();
		return itemDao.itemsBeforeStartAndAfterEndByUser(userId, new Timestamp(now.getTime()), new Timestamp(now.getTime()));
	}
	@Transactional
	@Override
	public int delete(long itemId) {
		return itemDao.delete(itemId);
	}
	@Transactional
	@Override
	public List<Item> search(String keyword, String categoryName, Double minPrice,
			Double maxPrice, String locationName) {
		Date now=new Date();
		return itemDao.search(keyword, categoryName, minPrice, maxPrice, locationName, new Timestamp(now.getTime()), new Timestamp(now.getTime()));
	}
	@Transactional
	@Override
	public List<Item> findAllActiveItems() {
		Date now=new Date();
		return itemDao.itemsBeforeStartAndAfterEnd(new Timestamp(now.getTime()), new Timestamp(now.getTime()));
	}
	@Transactional
	@Override
	public Item findItemByIdWithBids(long itemId) {
		return itemDao.findItemByIdWithBids(itemId);
	}
	@Transactional
	@Override
	public List<Item> findInActiveItemsByUser(long userId) {
		Date now=new Date();
		return itemDao.findItemsAfterStartDateByUser(userId, new Timestamp(now.getTime()));
	}
	@Transactional
	@Override
	public List<Item> findEndedItemsByUser(long userId) {
		Date now=new Date();
		return itemDao.findItemsBeforeEndDateByUser(userId, new Timestamp(now.getTime()));
	}

	

}
