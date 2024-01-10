package com.example.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mypage")
public class Mypage {
	
	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "favorit")
	private String favorit;
	
	@Column(name = "watch_list")
	private String watchList;
	
	@Column(name = "account_movie_list")
	private String accountMovieList;

	public Mypage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mypage(String account, String favorit, String watchList, String accountMovieList) {
		super();
		this.account = account;
		this.favorit = favorit;
		this.watchList = watchList;
		this.accountMovieList = accountMovieList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFavorit() {
		return favorit;
	}

	public void setFavorit(String favorit) {
		this.favorit = favorit;
	}

	public String getWatchList() {
		return watchList;
	}

	public void setWatchList(String watchList) {
		this.watchList = watchList;
	}

	public String getAccountMovieList() {
		return accountMovieList;
	}

	public void setAccountMovieList(String accountMovieList) {
		this.accountMovieList = accountMovieList;
	}
	
	

}
