package app.auctions.service;

import java.util.List;

import app.auctions.model.Message;
import app.auctions.model.User;

public interface UserService {
	public void save(User user);
    public List<User> listUsers();
    public User findUserByUsername(String username);
    public User findUserById(long userId);
    public List<User> findUsersByStatus(boolean enabled);
    public User findUserWithRolesByUsername(String username);
    public boolean isUsernameUnique(String username);
    public User findUserByStatusAndUsername(boolean enabled,String username);
}
