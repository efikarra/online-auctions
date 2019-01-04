package app.auctions.dao;

import java.sql.Timestamp;
import java.util.List;

import app.auctions.model.Bid;
import app.auctions.model.Item;

public interface ItemDao {
     public void save(Item item);
     public List<Item> listItems();
     public Item findItemById(long itemId);
     public Item findItemByIdWithBids(long itemId);
     public List<Bid> findItemBids(long itemId);
     public List<Item> findAllItemsByUser(long userId);
     public List<Item> itemsBeforeStartAndAfterEndByUser(long userId, Timestamp startDate, Timestamp endDate);
     public List<Item> findItemsAfterStartDateByUser(long userId, Timestamp startDate);
     public List<Item> findItemsBeforeEndDateByUser(long userId, Timestamp endDate);
     public List<Item> itemsBeforeStartAndAfterEnd(Timestamp startDate, Timestamp endDate);
     int delete(long itemId);
     public List<Item> search(String keyword, String categoryName, Double minPrice,
 			Double maxPrice, String locationName, Timestamp ends, Timestamp started);
}
