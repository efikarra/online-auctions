package app.auctions.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import app.auctions.model.Photo;

public class PhotoDaoImpl implements PhotoDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long save(Photo photo) {
		Long id = (Long) sessionFactory.getCurrentSession().save(photo);
		return id;

	}
	@Override
	public void updatePhotoPath(Long photoId,String path) {
		String hql="update Photo set photoPath= :path where photoId= :photoId";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter("path", path).setParameter("photoId", photoId).executeUpdate();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getAllPhotosByItem(long itemId) {
		return sessionFactory.getCurrentSession().createCriteria(Photo.class)
				.add(Restrictions.eq("item.itemId", itemId)).list();

	}

	@Override
	public Photo findPhotoById(long photoId) {
		return (Photo) sessionFactory.getCurrentSession()
				.createCriteria(Photo.class).add(Restrictions.idEq(photoId))
				.uniqueResult();
	}
}
