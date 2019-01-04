package app.auctions.spring.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.auctions.dto.BidDTO;
import app.auctions.dto.ItemDTO;
import app.auctions.dto.MessageDTO;
import app.auctions.dto.NewUserDTO;
import app.auctions.dto.UserDTO;
import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.model.Bid;
import app.auctions.model.Item;
import app.auctions.model.Message;
import app.auctions.model.Recommendation;
import app.auctions.model.Role;
import app.auctions.model.User;
import app.auctions.service.BidService;
import app.auctions.service.ItemService;
import app.auctions.service.MessageService;
import app.auctions.service.RecommendationService;
import app.auctions.service.RoleService;
import app.auctions.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
@RestController
@RequestMapping(value="/users")
public class UserRest {
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	MessageService messageService;
	@Autowired
	BidService bidService;
	@Autowired
	RecommendationService recommendationService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	HibernateMapperFactory mapperFactory;
	
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getUsers(@RequestParam(required=false) Boolean enabled,@RequestParam(required=false) String username) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String usersToJson = "";
		List<User> users = new ArrayList<User>();
		if (enabled== null&&username==null) {
			users.addAll(userService.listUsers());
		} else if(enabled!= null&&username==null){
			users.addAll(userService.findUsersByStatus(enabled));
		}
		else if(enabled!= null&&username!=null){
			users.add(userService.findUserByStatusAndUsername(enabled,username));
		}
		else {
			users.add(userService.findUserByUsername(username));
		}
		List<UserDTO> userDTOs = mapperFactory.getMapperFacade().mapAsList(
				users, UserDTO.class);
		try {
			usersToJson = mapper.writeValueAsString(userDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return usersToJson;

	}
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getExpert(@PathVariable long userId) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String userToJson = "";
		User user = userService.findUserById(userId);
		UserDTO userDTO = mapperFactory.getMapperFacade().map(
				user, UserDTO.class);

		try {
			userToJson = mapper.writeValueAsString(userDTO);
			System.out.println(userToJson);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return userToJson;

	}
	
	@RequestMapping(value = "/{userId}/enabled", method = RequestMethod.POST, headers = "Accept=application/json")
	public void saveExpert(@PathVariable("userId") long userId,
			@RequestBody boolean enabled) {
		User user = userService.findUserById(userId);
		user.setEnabled(enabled);
		userService.save(user);
	}
	@RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
	public void saveUser(@Valid @RequestBody NewUserDTO userDTO) {
		Role role = roleService.findRoleByName("ROLE_USER");
		User user = mapperFactory.getMapperFacade().map(userDTO,
				User.class);
		user.setEnabled(false);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role);
		userService.save(user);
	}
	@RequestMapping(value = "", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateUser(@Valid @RequestBody UserDTO userDTO) {
		Role role = roleService.findRoleByName(userDTO.getRole().getRoleName());
		User u=userService.findUserById(userDTO.getUserId());
		User user = mapperFactory.getMapperFacade().map(userDTO,
				User.class);
		user.setPassword(u.getPassword());
		user.setRole(role);
		userService.save(user);
	}
	@RequestMapping(value = "/{userId}/items", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserItems(@PathVariable long userId,@RequestParam(name="active",required=false) Boolean active) {
		
		List<Item> items=null;
		if(active==null)
		{
		items = itemService
				.findAllItemsByUser(userId);
		}
		else if(active){
			items = itemService
					.findActiveItemsByUser(userId);
		}
		else{
			items = itemService
					.findInActiveItemsByUser(userId);
			
			
		}
		List<ItemDTO> itemDTOs = mapperFactory.getMapperFacade()
				.mapAsList(items, ItemDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String itemsToJson = "";
		try {
			itemsToJson = mapper.writeValueAsString(itemDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return itemsToJson;

	}
	@RequestMapping(value = "/{userId}/items/ended", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserItems(@PathVariable long userId) {
		
		List<Item> items=itemService
					.findEndedItemsByUser(userId);
			
		List<ItemDTO> itemDTOs = mapperFactory.getMapperFacade()
				.mapAsList(items, ItemDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String itemsToJson = "";
		try {
			itemsToJson = mapper.writeValueAsString(itemDTOs);
			System.out.println(itemsToJson);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return itemsToJson;

	}
	@RequestMapping(value = "/{userId}/bids", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserBids(@PathVariable long userId,@RequestParam(name="active",required=false) Boolean active) {
		
		List<Bid> bids=null;
		if(active==null)
		{
		bids = bidService
				.findAllBidsByUser(userId);
		}
		else if(active){
			bids = bidService
					.findActiveBidsByUser(userId);
		}
		else{
			bids = bidService
					.findInActiveBidsByUser(userId);
			
			
		}
		List<BidDTO> bidDTOs = mapperFactory.getMapperFacade()
				.mapAsList(bids, BidDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String bidsToJson = "";
		try {
			bidsToJson = mapper.writeValueAsString(bidDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return bidsToJson;

	}
	@RequestMapping(value = "/{userId}/recommendation", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserRecommendation(@PathVariable long userId) {
		List<Recommendation> recs=recommendationService.findRecommendationsByUser(userId);
		List<Item> items=new ArrayList<Item>();
		for(Recommendation r: recs)
		{
			items.add(r.getItem());
		}
		List<ItemDTO> itemDTOs = mapperFactory.getMapperFacade()
				.mapAsList(items, ItemDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String itemsToJson = "";
		try {
			itemsToJson = mapper.writeValueAsString(itemDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return itemsToJson;

	}
	@RequestMapping(value = "/{userId}/messages/receivedMessages", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserReceivedMessages(@PathVariable long userId) {
		List<Message> messages = messageService
				.findInboxedMessagesByReceiver(userId);
		List<MessageDTO> messageDTOs = mapperFactory.getMapperFacade()
				.mapAsList(messages, MessageDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String messagesToJson = "";
		try {
			messagesToJson = mapper.writeValueAsString(messageDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return messagesToJson;

	}
	@RequestMapping(value = "/{userId}/messages/receivedMessages/{messageId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUserReceivedMessage(@PathVariable long userId,@PathVariable long messageId) {
		 messageService
				.deleteFromInbox(messageId);
		

	}
	@RequestMapping(value = "/{userId}/messages/sentMessages", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findUserSentMessages(@PathVariable long userId) {
		List<Message> messages = messageService
				.findOutboxedMessagesBySender(userId);
		List<MessageDTO> messageDTOs = mapperFactory.getMapperFacade()
				.mapAsList(messages, MessageDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String messagesToJson = "";
		try {
			messagesToJson = mapper.writeValueAsString(messageDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return messagesToJson;

	}
	@RequestMapping(value = "/{userId}/messages/sentMessages/{messageId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUserSentMessage(@PathVariable long userId,@PathVariable long messageId) {
		messageService
		.deleteFromOutbox(messageId);
	}
	
	
}
