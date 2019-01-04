package app.auctions.spring.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import app.auctions.dto.MessageDTO;
import app.auctions.dto.UserDTO;
import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.model.Message;
import app.auctions.model.User;
import app.auctions.service.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageRest {
	@Autowired
	MessageService messageService;
	@Autowired
	HibernateMapperFactory mapperFactory;

	@RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
	public void sendMessage(@RequestBody MessageDTO messageDTO) {
		Message message = mapperFactory.getMapperFacade().map(messageDTO,
				Message.class);
		message.setInboxStatus(true);
		message.setOutboxStatus(true);
		messageService.save(message);

	}
	@RequestMapping(value = "/{messageId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getMessage(@PathVariable long messageId) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String messageToJson = "";
		Message message = messageService.findMessageById(messageId);
		MessageDTO messageDTO = mapperFactory.getMapperFacade().map(
				message, MessageDTO.class);

		try {
			messageToJson = mapper.writeValueAsString(messageDTO);
			System.out.println(messageToJson);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return messageToJson;


	}
	@RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteMessage(@PathVariable long messageId) {

		messageService.deleteFromInbox(messageId);

	}
}
