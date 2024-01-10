package com.example.moive.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "movie")
	private String movie;
	
	@Id
	@Column(name = "comment_index")
	private int commentIndex;
	
	@Column(name = "comment_index_order")
	private int commentIndexOrder;
	
	@Column(name = "comment_text")
	private String commentText;
	
	@Column(name = "comment_time")
	private LocalDateTime commentTime;
	
	@Column(name = "like")
	private int like;
	
	@Column(name = "dislike")
	private int dislike;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String movie, int commentIndex, int commentIndexOrder, String commentText, LocalDateTime commentTime,
			int like, int dislike) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = commentIndexOrder;
		this.commentText = commentText;
		this.commentTime = commentTime;
		this.like = like;
		this.dislike = dislike;
	}
	
	//Creat使用的參數
	public Comment(String movie, int commentIndex, String commentText) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = 0;
		this.commentText = commentText;
		this.commentTime = LocalDateTime.now();
		this.like = 0;
		this.dislike = 0;
	}
	
	//like與dislike用的
	public Comment(String movie, int commentIndex, int commentIndexOrder, int like ,int dislike) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = commentIndexOrder;
		this.commentTime = LocalDateTime.now();
		this.like = 0;
		this.dislike = 0;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getCommentIndex() {
		return commentIndex;
	}

	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}

	public int getCommentIndexOrder() {
		return commentIndexOrder;
	}

	public void setCommentIndexOrder(int commentIndexOrder) {
		this.commentIndexOrder = commentIndexOrder;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
