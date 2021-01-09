package com.chugar.spring_security.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "simple_authoritie")
public class SimpleAuthoritie implements GrantedAuthority {
	
	
	/* FIELDS */
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "authoritie")
	private String authority;
	
	
	/* CONSTRUCTOR */
	public SimpleAuthoritie() {

	}

	public SimpleAuthoritie(String authority) {
		this.authority = authority;
	}


	/* GETTERS AND SETTERS */
	@Override
	public String getAuthority() {
		return this.authority;
	}

}
