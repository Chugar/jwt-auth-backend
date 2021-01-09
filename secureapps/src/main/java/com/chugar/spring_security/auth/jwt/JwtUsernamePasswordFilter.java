package com.chugar.spring_security.auth.jwt;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class JwtUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {
	
	
	AuthenticationManager authenticationManager;
	
		
	
	public JwtUsernamePasswordFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}



	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			AuthenticationModel authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationModel.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			return authenticationManager.authenticate(authentication);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
		String jwtToken = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authResult.getAuthorities() )
			.setIssuedAt(Date.valueOf(LocalDate.now()))
			.setExpiration(Date.valueOf(LocalDate.now().plusDays(7)))
			.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
			.compact();
		
		response.addHeader("Authorization", "Bearer " + jwtToken);
		
	}

}
