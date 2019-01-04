package app.auctions.recommendation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.jaxb.BidderJaxb;
import app.auctions.jaxb.ItemJaxb;
import app.auctions.jaxb.ItemsList;
import app.auctions.jaxb.SellerJaxb;
import app.auctions.model.Bid;
import app.auctions.model.Category;
import app.auctions.model.Item;
import app.auctions.model.User;
import app.auctions.service.CategoryService;
import app.auctions.service.ItemService;
import app.auctions.service.UserService;

public class XmlItemsIntoDatabase {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		Jaxb2Marshaller jaxb2Marshaller = (Jaxb2Marshaller) context
				.getBean("jaxb2Marshaller");
		HibernateMapperFactory mapper = (HibernateMapperFactory) context
				.getBean("hibernateMapperFactory");
		UserService userService = (UserService) context.getBean("userService");
		ItemService is = (ItemService) context.getBean("itemService");
		CategoryService categoryService = (CategoryService) context
				.getBean("categoryService");
		List<ItemsList> itemsLists = new ArrayList<ItemsList>();
		for (int i = 0; i < 40; i++) {
			ItemsList list = (ItemsList) jaxb2Marshaller
					.unmarshal(new StreamSource(new File(
							"C://Users/Efaki/Desktop/items/ebay-data/items-"
									+ i + ".xml")));
			itemsLists.add(list);
		}
		List<ItemJaxb> items = new ArrayList<ItemJaxb>();
		for (int i = 0; i < 40; i++) {
			items.addAll(itemsLists.get(i).getItems());
		}
		mapper.classMap(BidderJaxb.class, User.class)
				.field("userId", "username").byDefault().register();
		mapper.classMap(SellerJaxb.class, User.class)
				.field("userId", "username").byDefault().register();

		mapper.classMap(ItemJaxb.class, Item.class)
				.field("country", "location.country").byDefault().register();
		List<Item> items2 = mapper.getMapperFacade().mapAsList(items,
				Item.class);
		/*
		 * for (int i = 17860; i < items2.size(); i++) { for(Category c:
		 * items2.get(i).getCategories()) { System.out.println(i); Category
		 * cat=categoryService.findCategoryByName(c.getCategoryName());
		 * if(cat==null) { categoryService.save(c); } } }
		 */
		for (int i = 16753; i <= items2.size(); i++) {
			System.out.println(i);
			User u = userService.findUserByUsername(items2.get(i).getSeller()
					.getUsername());
			items2.get(i).setSeller(u);
			if (items2.get(i).getBids() != null) {
				for (Bid bid : items2.get(i).getBids()) {
					User u2 = userService.findUserByUsername(bid.getBidder()
							.getUsername());
					bid.setBidder(u2);
					bid.setItem(items2.get(i));
				}
			}
			if (items2.get(i).getLocation() != null) {
				items2.get(i).getLocation()
						.setCountry(items.get(i).getCountry());
			}
			System.out.println(items2.get(i).getItemId());
			Set<String> c2=new HashSet<String>();
			Set<Category> todel=new HashSet<Category>();
			for (Category c : items2.get(i).getCategories()) {
				Category cat = categoryService.findCategoryByName(c
						.getCategoryName());
				c.setCategoryId(cat.getCategoryId());
				
				if(!c2.contains(c.getCategoryName()))
				{
				    c2.add(c.getCategoryName());
				}
				else
				{
					System.out.println(c.getCategoryId()+"added to del");
					todel.add(c);
				}
				

			}
			if (!todel.isEmpty()) {
				System.out.println(todel.size());
				for(Category cd: todel){
				  items2.get(i).getCategories().remove(cd);}
			}
			//items2.get(i).getCategories().remove(c2);

			is.save(items2.get(i));
		}

	}
}
