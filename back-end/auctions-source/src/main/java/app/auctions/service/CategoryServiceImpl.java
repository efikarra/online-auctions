package app.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.CategoryDao;
import app.auctions.dao.ItemDao;
import app.auctions.model.Category;

public class CategoryServiceImpl implements CategoryService{
	
	private CategoryDao categoryDao;
	 
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
	@Transactional
	@Override
	public void save(Category category) {
		categoryDao.save(category);
		
	}

	@Transactional
	@Override
	public List<Category> listCategories() {
		return categoryDao.listCategories();
	}

	@Transactional
	@Override
	public Category findCategoryByName(String categoryName) {
		return categoryDao.findCategoryByName(categoryName);
	}

}
