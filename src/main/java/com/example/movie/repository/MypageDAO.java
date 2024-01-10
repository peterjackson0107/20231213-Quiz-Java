package com.example.movie.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Mypage;

@Repository
public interface MypageDAO extends JpaRepository<Mypage, String>{
	
	public Mypage findByAccount(String account);

	
}
