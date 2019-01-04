package app.auctions.service;

import java.util.List;

import app.auctions.model.Category;

public interface CategoryService {
	public void save(Category category);
    public List<Category> listCategories();
    public Category findCategoryByName(String categoryName);
}
