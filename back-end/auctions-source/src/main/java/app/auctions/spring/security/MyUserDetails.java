package app.auctions.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.auctions.model.Role;
import app.auctions.model.User;

public class MyUserDetails implements UserDetails{
	private User user;
	public MyUserDetails(User user) {
		this.user=user;
	}
	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();	
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
	return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
 
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
