package com.example.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "buy_seat")
public class BuySeat implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "movie_order")
	private int movieOrder;
	
	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "seat")
	private String seat;

	public BuySeat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuySeat(int movieOrder, String account, String seat) {
		super();
		this.movieOrder = movieOrder;
		this.account = account;
		this.seat = seat;
	}

	public int getMovieOrder() {
		return movieOrder;
	}

	public void setMovieOrder(int movieOrder) {
		this.movieOrder = movieOrder;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	

}
