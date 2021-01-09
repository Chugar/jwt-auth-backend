package com.chugar.spring_security.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	@GetMapping(value="/signin")
	public String getLogin() {
		return "signin";
	}
	
	
	@GetMapping(value="/signout")
	public String getLogout() {
		return "signout";
	}
	

}
