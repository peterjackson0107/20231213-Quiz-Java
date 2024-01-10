package com.example.movie.vo;

public class CommentReq {
	
	private int commentIndex;
	
	private int commentIndexOrder;
	
	private String movie;
	
	private String commentText;
	
	private int like;
	
	private int dislike;

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	
	
	
}
