package app.auctions.spring.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import app.auctions.dto.BidDTO;
import app.auctions.dto.ItemDTO;
import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.jaxb.ItemJaxb;
import app.auctions.jaxb.ItemsList;
import app.auctions.model.Bid;
import app.auctions.model.Item;
import app.auctions.model.Photo;
import app.auctions.service.BidService;
import app.auctions.service.ItemService;
import app.auctions.service.PhotoService;
import app.auctions.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@RestController
@RequestMapping(value="items")
public class ItemRest {
	@Autowired
	ItemService itemService;
	@Autowired
	PhotoService photoService;
	@Autowired
	BidService bidService;
	@Autowired
	HibernateMapperFactory mapperFactory;
	@Autowired
	UserService userService;
	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;
	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	public String getItem(@PathVariable("itemId") long itemId) 
	{
		Item item=itemService.findItemById(itemId);
		ItemDTO itemDTO=mapperFactory.getMapperFacade().map(item,
			ItemDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String itemToJson = "";
		try {
			itemToJson = mapper.writeValueAsString(itemDTO);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return itemToJson;
	}
	@RequestMapping(value = "", method ={ RequestMethod.POST,RequestMethod.PUT})
	public String saveItem(@RequestBody ItemDTO itemDTO) 
	{
		Item item = mapperFactory.getMapperFacade().map(itemDTO,
				Item.class);
		item.setCurrently(itemDTO.getFirstBid());
		item.setNumberOfBids(0);
		itemService.save(item);
		ItemDTO itemDTO2=mapperFactory.getMapperFacade().map(item,
				ItemDTO.class);
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Hibernate4Module());
			String itemToJson = "";
			try {
				itemToJson = mapper.writeValueAsString(itemDTO2);
			} catch (IOException e) {

				e.printStackTrace();
			}

			return itemToJson;
	}
	@RequestMapping(value = "/json", method = RequestMethod.GET,headers = "Accept=application/json")
	public String getItems() 
	{
		List<Item> items=itemService.findAllActiveItems();
		List<ItemDTO> itemDTOs = mapperFactory.getMapperFacade().mapAsList(items,
				ItemDTO.class);
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
	@RequestMapping(value = "/xml", method = RequestMethod.GET,produces="application/xml")
	public ByteArrayResource getItemsXml(HttpServletResponse  response) 
	{
		 Map<String, Object> props = new HashMap<String, Object>();
		    props.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		response.setHeader("Content-Disposition", "attachment; filename=items.xml");
		List<Item> items=itemService.listItems();
		mapperFactory.classMap(Item.class, ItemJaxb.class)
		.field("location.country", "country").byDefault().register();
		List<ItemJaxb> itemJaxbs = mapperFactory.getMapperFacade().mapAsList(items,
				ItemJaxb.class);
		ItemsList list=new ItemsList();
		list.setItems(itemJaxbs);
		ByteArrayOutputStream  out = new ByteArrayOutputStream();	
		StreamResult str=new StreamResult(out);
		jaxb2Marshaller.setMarshallerProperties(props);
		jaxb2Marshaller.marshal(list, str);
		ByteArrayResource b= new ByteArrayResource(out.toByteArray());
		return b;
		

	}

	@RequestMapping(value = "/{itemId}/bids", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findItemBids(@PathVariable("itemId") long itemId) {

		List<Bid> bids = itemService.findItemBids(itemId);
		List<BidDTO> bidDTOs = mapperFactory.getMapperFacade().mapAsList(bids,
				BidDTO.class);
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

	@RequestMapping(value = "/{itemId}/bids", method = RequestMethod.POST, headers = "Accept=application/json")
	public void saveItemBid(@Valid @RequestBody BidDTO bidDTO,
			@PathVariable("itemId") long itemId) {
		Item item=itemService.findItemById(itemId);
		Bid bid = mapperFactory.getMapperFacade().map(bidDTO, Bid.class);
		bid.setItem(item);
		bidService.save(bid);

	}
	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public String search(@RequestParam String categoryName,
			@RequestParam(value="keyword",required=false) String keyword,@RequestParam(value="minPrice",required=false) Double minPrice,@RequestParam(value="maxPrice",required=false) Double maxPrice,@RequestParam(value="locationName",required=false) String locationName) {
		System.out.println(categoryName);
		if(categoryName.equalsIgnoreCase("All categories"))
		{
			categoryName=null;
			System.out.println(categoryName);
		}
		List<Item>  items=itemService.search(keyword, categoryName,minPrice, maxPrice,locationName);
		
		List<ItemDTO> itemDTOs = mapperFactory.getMapperFacade().mapAsList(items,
				ItemDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String itemsToJson="";
		try {
			itemsToJson = mapper.writeValueAsString(itemDTOs);	
		} catch (IOException e) {

			e.printStackTrace();
		}
			
		
		return itemsToJson;

	}
	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteProject(@PathVariable("itemId") long itemId) {
		itemService.delete(itemId);

	}
	@RequestMapping(value = "/{itemId}/bids/{bidId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteProjectBid(@PathVariable("itemId") long itemId,
			@PathVariable("bidId") long bidId) {

		bidService.delete(bidId);

	}
	@RequestMapping(value = "/{itemId}/photos", method = RequestMethod.POST,consumes = "multipart/form-data")
	public void uploadPhoto(@RequestParam(name="file") CommonsMultipartFile file,@PathVariable long itemId) {
		Item item=itemService.findItemById(itemId);
		Photo photo=new Photo();
		photo.setItem(item);
		photo.setPhotoPath("");
		Long photoId=photoService.save(photo);
		
		String fileName=file.getOriginalFilename();
		System.out.println(fileName);
		String path="C://auctions-photos/"+photoId+"."+fileName.substring(fileName.lastIndexOf('.') + 1);
		photoService.updatePhotoPath(photoId, path);
		try {						
			File file2=new File("C://auctions-photos/"+photoId+"."+fileName.substring(fileName.lastIndexOf('.') + 1));
			file.transferTo(file2);		
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/{itemId}/photos/{photoId}", method = RequestMethod.GET, headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
	public BufferedImage  getPhoto(@PathVariable long photoId) {
		Photo photo=photoService.findPhotoById(photoId);
		 try {
		        InputStream inputStream = new FileInputStream(new File(photo.getPhotoPath()));
		        return ImageIO.read(inputStream);
		    } catch (IOException e) {
		        throw new RuntimeException(e);
		    }
	}
}
