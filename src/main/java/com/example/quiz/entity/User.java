package com.example.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @ : annotation

@Entity //��spring boot�h���U�� �����U@���@�� entity������
@Table(name = "user") //�Ыت��(name = "mySQL�������W��")
public class User {

	@Id   //���bmySQL��̦�����pk�����
	@Column(name = "account") //name = "������W��"
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
