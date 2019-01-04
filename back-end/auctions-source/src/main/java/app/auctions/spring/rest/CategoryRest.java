package app.auctions.spring.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.auctions.dto.CategoryDTO;
import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.model.Category;
import app.auctions.service.CategoryService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@RestController
@RequestMapping("/categories")
public class CategoryRest {
	@Autowired
	CategoryService categoryService;
	@Autowired
	HibernateMapperFactory mapperFactory;
	
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCategories() {
		List<Category> categories=categoryService.listCategories();
		List<CategoryDTO> categoryDTOs = mapperFactory.getMapperFacade()
				.mapAsList(categories, CategoryDTO.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		String categoriesToJson = "";
		try {
			categoriesToJson = mapper.writeValueAsString(categoryDTOs);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return categoriesToJson;

	}
}
