package com.chugar.spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chugar.spring_security.dao.SimpleUserRepository;


@Service
public class SimpleUserService implements UserDetailsService {
	
	
	private SimpleUserRepository simpleUserRepository;	
	
	
	
	public SimpleUserService() {}


	@Autowired
	public SimpleUserService(SimpleUserRepository repository) {
		this.simpleUserRepository = repository;
	}


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.simpleUserRepository.findByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException("User not found"));
	}			

}
