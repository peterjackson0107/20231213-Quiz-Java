package com.example.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "artwork")
public class Artwork {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "art_order")
	private int artOrder;
	
	@Column(name = "movie")
	private String movie;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "art_name")
	private String artName;
	
	@Column(name = "art_description")
	private String artDescription;

	public Artwork() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artwork(int artOrder, String movie, String account, String artName, String artDescription) {
		super();
		this.artOrder = artOrder;
		this.movie = movie;
		this.account = account;
		this.artName = artName;
		this.artDescription = artDescription;
	}
	
	public Artwork(String movie, String account, String artName, String artDescription) {
		super();
		this.movie = movie;
		this.account = account;
		this.artName = artName;
		this.artDescription = artDescription;
	}

	public int getArtOrder() {
		return artOrder;
	}

	public void setArtOrder(int artOrder) {
		this.artOrder = artOrder;
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

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public String getArtDescription() {
		return artDescription;
	}

	public void setArtDescription(String artDescription) {
		this.artDescription = artDescription;
	}
	
	


}
