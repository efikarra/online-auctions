package app.auctions.service;

import java.util.List;

import app.auctions.model.Bid;
import app.auctions.model.Item;

public interface ItemService {
	 public void save(Item item);
     public List<Item> listItems();
     public Item findItemById(long itemId);
     public List<Bid> findItemBids(long itemId);
     public List<Item> findAllItemsByUser(long userId);
     public List<Item> findActiveItemsByUser(long userId);
     public List<Item> findInActiveItemsByUser(long userId);
     public List<Item> findEndedItemsByUser(long userId);
     public List<Item> findAllActiveItems();
     int delete(long itemId);
     public Item findItemByIdWithBids(long itemId);
     public List<Item> search(String keyword, String categoryName,Double minPrice,Double maxPrice, String locationName);
}
