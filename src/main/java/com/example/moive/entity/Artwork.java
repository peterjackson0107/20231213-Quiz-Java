package com.example.moive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "artwork")
public class Artwork implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "movie")
	private String movie;

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "art_order")
	private int artOrder;

	@Column(name = "art_name")
	private String artName;

	public Artwork() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artwork(String movie, String account, int artOrder, String artName) {
		super();
		this.movie = movie;
		this.account = account;
		this.artOrder = artOrder;
		this.artName = artName;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getArtOrder() {
		return artOrder;
	}

	public void setArtOrder(int artOrder) {
		this.artOrder = artOrder;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

}
