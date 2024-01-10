package com.example.movie.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, String>{
	
	@Transactional
	public int deleteByCommentIndexAndCommentIndexOrderAndMovie(int commentIndex,int commentIndexOrder, String movie);
	
	public Optional<Comment> findByCommentIndexAndCommentIndexOrderAndMovie(int commentIndex,int commentIndexOrder, String movie);
	
	public Optional<Comment> findAllByMovie(String movie);
	
	public Optional<Comment> findTopByMovieAndCommentIndexOrderByCommentIndexOrderDesc(String movie, int commentIndex);
	
}
