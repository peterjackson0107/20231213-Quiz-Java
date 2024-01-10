package com.example.movie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "number")
	private String number;	

	@Column(name = "movie")
	private String movie;

	@Column(name = "comment_index")
	private int commentIndex;

	@Column(name = "comment_index_order")
	private int commentIndexOrder;

	@Column(name = "comment_text")
	private String commentText;

	@Column(name = "comment_time")
	private LocalDateTime commentTime;

	@Column(name = "favorite")
	private int favorite;

	@Column(name = "dislike")
	private int dislike;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int number,String movie, int commentIndex, int commentIndexOrder, String commentText, LocalDateTime commentTime,
			int like, int dislike) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = commentIndexOrder;
		this.commentText = commentText;
		this.commentTime = commentTime;
		this.favorite = like;
		this.dislike = dislike;
	}

	// Creatㄏノ把计
	public Comment(String movie, int commentIndex, String commentText) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = 0;
		this.commentText = commentText;
		this.commentTime = LocalDateTime.now();
		this.favorite = 0;
		this.dislike = 0;
	}
	
	// Creatchildㄏノ把计
	public Comment(String movie, int commentIndex,int commentIndexOrder, String commentText) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
		this.commentIndexOrder = commentIndexOrder;
		this.commentText = commentText;
		this.commentTime = LocalDateTime.now();
		this.favorite = 0;
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

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
