package com.example.mopa.thymeleaf.security;


import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	UserEntity user =userRepository.getUserByUsername(username);
	    	if(user==null) {
	    		throw new UsernameNotFoundException("Could not find User");
	    	}
	    	return new MyUserDetails(user);
	}

}
