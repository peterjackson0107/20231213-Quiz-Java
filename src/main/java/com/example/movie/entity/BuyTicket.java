package com.example.movie.entity;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buy_ticket")
public class BuyTicket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order")
	private int order;
	
	@Id
	@Column(name = "movie_name")
	private int movieName;
	
	@Column(name = "cinema")
	private String cinema;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "on_time")
	private LocalTime onTime;

	public BuyTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuyTicket(int order, int movieName, String cinema, String area, int price, LocalDate startDate,
			LocalDate endDate, LocalTime onTime) {
		super();
		this.order = order;
		this.movieName = movieName;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.onTime = onTime;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getMovieName() {
		return movieName;
	}

	public void setMovieName(int movieName) {
		this.movieName = movieName;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getOnTime() {
		return onTime;
	}

	public void setOnTime(LocalTime onTime) {
		this.onTime = onTime;
	}
	
	

}
