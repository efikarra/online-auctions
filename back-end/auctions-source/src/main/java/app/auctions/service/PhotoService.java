package app.auctions.service;

import java.util.List;

import app.auctions.model.Photo;

public interface PhotoService {
    public Long save(Photo photo);
    public List<Photo> getAllPhotosByItem(long itemId);
    public Photo findPhotoById(long photoId);
    public void updatePhotoPath(Long photoId,String path);
}
