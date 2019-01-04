package app.auctions.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name",nullable=false,unique=true)
	private String categoryName;
	
	@ManyToMany(mappedBy="categories",fetch=FetchType.LAZY)
    private Set<Item> items;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void seCategorytName(String name) {
		this.categoryName = name;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
