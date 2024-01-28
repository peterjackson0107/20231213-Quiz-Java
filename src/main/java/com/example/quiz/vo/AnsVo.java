package com.example.quiz.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnsVo {

	private int num;

	private String quizName;

	private String description;

	private LocalDate startDate;

	private LocalDate endDate;

	private String writer;

	private String answer;

	private LocalDateTime writeDateTime;

	public AnsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnsVo(int num, String quizName, String description, LocalDate startDate, LocalDate endDate, String writer,
			String answer, LocalDateTime writeDateTime) {
		super();
		this.num = num;
		this.quizName = quizName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.writer = writer;
		this.answer = answer;
		this.writeDateTime = writeDateTime;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getWriteDateTime() {
		return writeDateTime;
	}

	public void setWriteDateTime(LocalDateTime writeDateTime) {
		this.writeDateTime = writeDateTime;
	}

}
