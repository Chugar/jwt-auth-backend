package com.chugar.spring_security.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "simple_user")
public class SimpleUser implements UserDetails {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
		
	@Column(name = "password")
	private String password;
	
	@Column(name = "username")
	private String username;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<SimpleAuthoritie> authorities;
	
	
	

	public SimpleUser() {	}

	public SimpleUser(Set<SimpleAuthoritie> authorities, String password, String username) {
		this.authorities = authorities;
		this.password = password;
		this.username = username;
	}

	@Override
	public Set<SimpleAuthoritie> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
