package com.metaltraders.metallica.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaltraders.metallica.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);

}