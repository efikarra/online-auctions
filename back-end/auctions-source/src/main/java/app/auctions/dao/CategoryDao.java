package app.auctions.dao;

import java.util.List;

import app.auctions.model.Category;
import app.auctions.model.Item;

public interface CategoryDao {
	public void save(Category category);
    public List<Category> listCategories();
    public Category findCategoryByName(String categoryName);
}
