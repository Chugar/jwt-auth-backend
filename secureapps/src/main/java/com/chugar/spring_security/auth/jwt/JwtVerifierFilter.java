package com.chugar.spring_security.auth.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chugar.spring_security.entities.SimpleAuthoritie;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtVerifierFilter extends OncePerRequestFilter {	
	
	private JwtProps jwtprops;


	public JwtVerifierFilter(JwtProps jwtprops) {
		this.jwtprops = jwtprops;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				
		String header = request.getHeader("Authorization");
		
		if(header == null || header.isEmpty() || !header.startsWith(jwtprops.getPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = header.replace(jwtprops.getPrefix(),"");
		
		Jws<Claims> parse = Jwts.parser()
				.setSigningKey(jwtprops.getKey().getBytes())
				.parseClaimsJws(token);
		
		Claims body = parse.getBody();
		
		String username = body.getSubject();
		List<Map<String, String>> rawAuthorities = (List<Map<String, String>>) body.get("authorities");
		
		Set<SimpleAuthoritie> authorities = rawAuthorities.stream()
			.map(m -> new SimpleAuthoritie(m.get("authority") ))
			.collect(Collectors.toSet());
				
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);

	}



	

}
