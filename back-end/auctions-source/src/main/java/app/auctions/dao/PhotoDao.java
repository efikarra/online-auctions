package app.auctions.dao;

import java.util.List;

import app.auctions.model.Photo;

public interface PhotoDao {
    public Long save(Photo photo);
    public List<Photo> getAllPhotosByItem(long itemId);
    public Photo findPhotoById(long photoId);
    public void updatePhotoPath(Long photoId,String path);
}
