package com.chugar.spring_security.auth.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtProps {
	
	@Value("${application.jwt.tokenPrefix}")
	private String prefix;
	
	@Value("${application.jwt.signKey}")
	private String key;


	public JwtProps() {	}

	public String getPrefix() {
		return prefix;
	}

	public String getKey() {
		return key;
	}

}
