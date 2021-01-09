package com.chugar.spring_security.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chugar.spring_security.services.SimpleUserService;;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HomeController {
	
	
	private SimpleUserService service;
	private PasswordEncoder encoder;
	
	
	@Autowired
	public HomeController(SimpleUserService service, PasswordEncoder encoder) {
		this.service = service;
		this.encoder = encoder;
	}





	@GetMapping
	public UserDetails home() {
		
		String encode = encoder.encode("hello");
		System.out.println(encode);
		
		return this.service.loadUserByUsername("chill");
	}
	
	

}
