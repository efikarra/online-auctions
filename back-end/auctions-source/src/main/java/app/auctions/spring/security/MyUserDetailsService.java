package app.auctions.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.auctions.model.User;
import app.auctions.service.UserService;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
    private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.findUserWithRolesByUsername(username);
		
		if(user == null){
			throw new UsernameNotFoundException("UserName "+username+" not found");
		}
		return new MyUserDetails(user);
	}

}
