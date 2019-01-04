package app.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import app.auctions.dao.ItemDao;
import app.auctions.dao.UserDao;
import app.auctions.model.Message;
import app.auctions.model.User;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	 
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
	@Transactional
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Transactional
	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Transactional
	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Transactional
	@Override
	public boolean isUsernameUnique(String username) {
		return userDao.isUsernameUnique(username);
	}
	@Transactional
	@Override
	public User findUserWithRolesByUsername(String username) {
		return userDao.findUserWithRolesByUsername(username);
	}
	@Transactional
	@Override
	public User findUserById(long userId) {
		return userDao.findUserById(userId);
	}
	@Transactional
	@Override
	public List<User> findUsersByStatus(boolean enabled) {
		return userDao.findUsersByStatus(enabled);
	}

	@Override
	public User findUserByStatusAndUsername(boolean enabled, String username) {
		return userDao.findUserByStatusAndUsername(enabled, username);
	}

}
