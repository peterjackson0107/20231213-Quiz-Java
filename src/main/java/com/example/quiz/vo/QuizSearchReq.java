package com.example.quiz.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizSearchReq {

	@JsonProperty("quiz_name")
	private String quizName;

	@JsonProperty("start_date")
	private LocalDate startDate;
	
	@JsonProperty("end_date")
	private LocalDate endDate;
	
	@JsonProperty("is_login")
	private boolean isLogin;

	public QuizSearchReq() {
		super();
	}

	public QuizSearchReq(String quizName, LocalDate startDat, LocalDate endDate, boolean isLogin) {
		super();
		this.quizName = quizName;
		this.startDate = startDat;
		this.endDate = endDate;
		this.isLogin = isLogin;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public LocalDate getStartDat() {
		return startDate;
	}

	public void setStartDat(LocalDate startDat) {
		this.startDate = startDat;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
}
