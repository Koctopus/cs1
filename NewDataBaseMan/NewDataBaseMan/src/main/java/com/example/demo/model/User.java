package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user_Info6")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    // JPA requirement
    protected User() {}
    
    public User(String username, String user_password,boolean isEditor) {
    	setId(0);
    	setUsername(username);
    	setPassword(user_password);
    	setEnabled(true);
    	setEditor(isEditor);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String userpassword;

    @Column(nullable = false)
    private boolean enabled;
    
    @Column(nullable = false)
    private boolean editor;
    
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
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
    	this.id = id;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return userpassword;
    }

    public void setPassword(String password) {
        this.userpassword = password;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isEditor() {
    	return editor;
    }
    
    public void setEditor(boolean editor) {
    	this.editor = editor;
    }
    
    @Override
   	public Collection<? extends GrantedAuthority> getAuthorities() {
   		// TODO Auto-generated method stub
   		Collection<GrantedAuthority> authorityList = new ArrayList<>();
   		if(this.isEditor()) {
   			System.out.println("isEditor is "+this.isEditor());
   			authorityList.add(new SimpleGrantedAuthority("ROLE_EDITOR"));
   		}else {
   			System.out.println("isEditor is "+this.isEditor());
   	   		authorityList.add(new SimpleGrantedAuthority("ROLE_LEARNER"));
   		}
   		return authorityList;
   	}

}