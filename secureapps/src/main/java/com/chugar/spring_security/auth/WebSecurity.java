package com.chugar.spring_security.auth;

import static com.chugar.spring_security.auth.authorities.Roles.STUDENT;
import static com.chugar.spring_security.auth.authorities.Roles.TEACHER;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.chugar.spring_security.auth.jwt.JwtProps;
import com.chugar.spring_security.auth.jwt.JwtUsernamePasswordFilter;
import com.chugar.spring_security.auth.jwt.JwtVerifierFilter;
import com.chugar.spring_security.services.SimpleUserService;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	private final PasswordEncoder encoder;
	private final SimpleUserService userService;
	private final JwtProps jwtProps;
	
	
	@Autowired
	public WebSecurity(PasswordEncoder encoder, SimpleUserService userService, JwtProps jwtProps) {
		this.encoder = encoder;
		this.userService = userService;
		this.jwtProps = jwtProps;
	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			.and()
			.addFilter(new JwtUsernamePasswordFilter(authenticationManager()))
			.addFilterAfter(new JwtVerifierFilter(jwtProps), JwtUsernamePasswordFilter.class)
			
			.authorizeRequests()
			
			.antMatchers("/home").anonymous()
			.antMatchers("/auth").permitAll()
			.antMatchers("/student").hasAnyRole(STUDENT.name(), TEACHER.name())
			.antMatchers("/teacher").hasRole(TEACHER.name())
			
			.anyRequest().authenticated();
			
		http.cors();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	protected AuthenticationManager getAuthManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(this.encoder);
		provider.setUserDetailsService(this.userService);
		return provider;
	}

}

