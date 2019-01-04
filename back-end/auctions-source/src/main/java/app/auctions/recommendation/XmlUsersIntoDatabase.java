package app.auctions.recommendation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.jaxb.BidJaxb;
import app.auctions.jaxb.BidderJaxb;
import app.auctions.jaxb.ItemJaxb;
import app.auctions.jaxb.ItemsList;
import app.auctions.jaxb.SellerJaxb;
import app.auctions.model.Role;
import app.auctions.model.User;
import app.auctions.service.ItemService;
import app.auctions.service.RoleService;
import app.auctions.service.UserService;

public class XmlUsersIntoDatabase {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		Jaxb2Marshaller jaxb2Marshaller = (Jaxb2Marshaller) context
				.getBean("jaxb2Marshaller");
		HibernateMapperFactory mapper = (HibernateMapperFactory) context
				.getBean("hibernateMapperFactory");
		RoleService roleService = (RoleService) context.getBean("roleService");
		UserService userService = (UserService) context.getBean("userService");
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		ItemService is = (ItemService) context.getBean("itemService");
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
		List<String> bidders = new ArrayList<String>();
		int pcode = 12345;
		int stno = 1;
		for (int i = 0; i < items.size(); i++) {
			List<BidJaxb> bids = items.get(i).getBids();
			for (int j = 0; j < bids.size(); j++) {
				
				if (bids.get(j).getBidder() != null) {
					
					BidderJaxb b = bids.get(j).getBidder();
					if (!bidders.contains(b.getUserId())) {
					bidders.add(b.getUserId());
					Role role = roleService.findRoleByName("ROLE_USER");
					User u = new User();
					u.setUsername(b.getUserId());
					u.setBidderRating(b.getRating());
					if(b.getCountry()==null)
					{
						u.setCountry("test");
					}
					else
					{
					u.setCountry(b.getCountry());
					}
					if(b.getLocation()==null)
					{
						u.setLocation("test");
					}
					else
					{
						u.setLocation(b.getLocation());
					}
					u.setEmail(b.getUserId() + "@gmail.com");
					u.setEnabled(true);
					u.setPhoneNumber("12345678");
					u.setFirstname(b.getUserId());
					u.setLastname(b.getUserId());
					u.setPassword(passwordEncoder.encode("123456"));
					u.setPostCode(pcode);
					u.setRole(role);
					u.setStreet("alexandrou");
					u.setStreetNumber(stno);
					u.setVatNumber(b.getUserId() + "1234");
					pcode++;
					stno++;
					userService.save(u);
				}
				}
			}
		}
		List<String> sellers = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getSeller() != null) {
				Role role = roleService.findRoleByName("ROLE_USER");
				SellerJaxb s = items.get(i).getSeller();
				if (bidders.contains(s.getUserId())) {
					User user = userService.findUserByUsername(s.getUserId());
					user.setSellerRating(s.getRating());
					userService.save(user);
				} else if(!sellers.contains(s.getUserId())){
					sellers.add(s.getUserId());
					User u = new User();
					u.setUsername(s.getUserId());
					u.setSellerRating(s.getRating());
					u.setCountry("test");
					u.setLocation("test");
					u.setEmail(s.getUserId() + "@gmail.com");
					u.setEnabled(true);
					u.setPhoneNumber("12345678");
					u.setFirstname(s.getUserId());
					u.setLastname(s.getUserId());
					u.setPassword(passwordEncoder.encode("123456"));
					u.setPostCode(pcode);
					u.setRole(role);
					u.setStreet("alexandrou");
					u.setStreetNumber(stno);
					u.setVatNumber(s.getUserId() + "1234");
					pcode++;
					stno++;
					userService.save(u);
				}
			}
		}
		/*
		 * mapper.classMap(BidderJaxb.class, User.class) .field("userId",
		 * "username").byDefault().register(); mapper.classMap(SellerJaxb.class,
		 * User.class) .field("userId", "username").byDefault().register();
		 * mapper.classMap(ItemJaxb.class, Item.class) .field("country",
		 * "location.country").byDefault().register(); List<Item>
		 * items2=mapper.getMapperFacade().mapAsList(items, Item.class);
		 */

		/*
		 * for (int i = 0; i < items2.size(); i++) {
		 * items.addAll(itemsLists.get(i).getItems()); }
		 */
	}
}
