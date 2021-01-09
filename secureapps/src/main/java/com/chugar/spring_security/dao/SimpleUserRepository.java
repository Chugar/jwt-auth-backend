package com.chugar.spring_security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chugar.spring_security.entities.SimpleUser;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, String>{

	Optional<SimpleUser> findByUsername(String username);
}
