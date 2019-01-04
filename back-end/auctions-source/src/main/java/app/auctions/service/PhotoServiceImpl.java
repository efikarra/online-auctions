package app.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.PhotoDao;
import app.auctions.model.Photo;

public class PhotoServiceImpl implements PhotoService{
	private PhotoDao photoDao;
	 
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }
    
	@Transactional
	@Override
	public Long save(Photo photo) {
		return photoDao.save(photo);	
	}
	@Transactional
	@Override
	public List<Photo> getAllPhotosByItem(long itemId) {	
		return photoDao.getAllPhotosByItem(itemId);
	}
	@Transactional
	@Override
	public Photo findPhotoById(long photoId) {
		return photoDao.findPhotoById(photoId);
	}
	@Transactional
	@Override
	public void updatePhotoPath(Long photoId,String path){
		photoDao.updatePhotoPath(photoId,path);
		
	}

}
