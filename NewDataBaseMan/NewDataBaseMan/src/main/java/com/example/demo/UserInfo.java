package com.example.demo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class UserInfo implements UserDetails {

	private static final long serialVersionUID = -256740067874995659L;
	
	private User user;	
	private Collection<GrantedAuthority> authorities;
		
	protected UserInfo(){}
	
	public UserInfo(User user,Collection<GrantedAuthority> authorities){
		this.user = user;
		this.authorities = authorities;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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

}    