package com.chugar.spring_security.presentation;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chugar.spring_security.auth.jwt.AuthenticationModel;
import com.chugar.spring_security.entities.SimpleAuthoritie;
import com.chugar.spring_security.entities.SimpleUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	AuthenticationManager authenticationManager;
	
	
	@Autowired	
	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@PostMapping
	public ResponseEntity<SimpleUser> getAuthentication (@RequestBody AuthenticationModel authModel) throws Exception {
			
		Authentication authenticated;
		try {
			String username = authModel.getUsername(); 
			String password = authModel.getPassword();
			authenticated = authenticationManager.authenticate(	new UsernamePasswordAuthenticationToken(username, password));
				
		} catch(BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		String jwtToken = Jwts.builder()
				.setSubject(authenticated.getName())
				.claim("authorities", authenticated.getAuthorities() )
				.setIssuedAt(Date.valueOf(LocalDate.now()))
				.setExpiration(Date.valueOf(LocalDate.now().plusDays(7)))
				.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
				.compact();
		
		Set<SimpleAuthoritie> userAutorities = authenticated.getAuthorities().stream()
			.map(a -> new SimpleAuthoritie(a.getAuthority()))
			.collect(Collectors.toSet());
		
		SimpleUser simpleUser = new SimpleUser(userAutorities, "", authenticated.getName());
		
		
		return ResponseEntity.ok()
				.header("Authorization", "Bearer " + jwtToken)
				.body(simpleUser);
		
		
	}

}
