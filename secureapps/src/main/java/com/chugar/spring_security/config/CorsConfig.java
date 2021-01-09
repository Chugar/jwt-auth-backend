package com.chugar.spring_security.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		
		System.out.println("Cors config triggered");
	    final CorsConfiguration config = new CorsConfiguration();
	    
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    config.setExposedHeaders(Arrays.asList("Authorization"));

	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

	    return bean;
	}

}
