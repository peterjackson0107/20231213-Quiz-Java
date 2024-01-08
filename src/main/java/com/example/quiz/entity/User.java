package com.example.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @ : annotation

@Entity //讓spring boot去做託管 讓底下@有作用 entity為表格用
@Table(name = "user") //創建表格(name = "mySQL內的表格名稱")
public class User {

	@Id   //指在mySQL表裡有打勾pk的欄位
	@Column(name = "account") //name = "表格欄位名稱"
	private String account;

	@Column(name = "password")
	private String pwd;

	public User() {
		super();
	}

	public User(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
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

}
