package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//@Repository 안해도 됨!
public interface UserRepository extends JpaRepository<User, Integer> {
	//jpa 네이밍 쿼리(전략)
	//SELECT*FROM user WHERE username=? AND password = ?
	User findByUsernameAndPassword(String username, String password);
	}
