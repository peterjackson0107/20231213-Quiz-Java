package com.example.quiz.vo;

import com.example.quiz.constants.RtnCode;

public class UserLoginRes {

	private RtnCode rtnCode;

	public UserLoginRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public UserLoginRes() {
		super();
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}
}
