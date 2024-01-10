package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, String>{
	
	public User findByAccountAndPwd(String account,String pwd);
	
	public boolean existsByAccountAndPwd(String account,String pwd);
}
