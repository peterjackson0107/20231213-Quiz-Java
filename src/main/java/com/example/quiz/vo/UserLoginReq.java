package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginReq {

	private String account;

	// 將外面對應key值(password)塞進變數名稱(pwd)內
	@JsonProperty("password")
	private String pwd;

	//無一般及參數建構方法
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
