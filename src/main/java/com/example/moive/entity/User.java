package com.example.moive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	//GeneratedValue可以讓資料存進去後立刻得到DB中AI產生的流水號值
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "order")
	private int order;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "password")
	private String pwd;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private int phone;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int order, String name, String account, String pwd, String email, int phone) {
		super();
		this.order = order;
		this.name = name;
		this.account = account;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	

}
