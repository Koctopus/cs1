package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return user;
    }
    
    private Collection<GrantedAuthority> getAuthorities(User user){
		
		if(user.isEditor()){
			return AuthorityUtils.createAuthorityList("ROLE_EDITOR");
		}else{
			return AuthorityUtils.createAuthorityList("ROLE_LEARNER");
		}
	}
    
    @Transactional
    public void registerEditor(String username, String password,boolean role) {
        User user = new User(username, passwordEncoder.encode(password),true);
        userRepository.save(user);
    }

    @Transactional
    public void registerLearner(String username, String password,boolean role) {
        User user = new User(username, passwordEncoder.encode(password),false);
        userRepository.save(user);
    }

}