package app.auctions.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "item_photo")
public class Photo {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="photo_id")
	private long photoId;
	
	@Column(name="photo_path",nullable=false)
	private String photoPath;
	
	@ManyToOne
    @JoinColumn(name = "item_id",nullable=false) 
	private Item item;
	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
