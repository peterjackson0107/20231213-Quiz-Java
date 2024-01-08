package com.example.quiz.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ���y����(mySQL ai���Ī�����)�h���Y�ɧ�s
	// ����� save �i DB ��i�o��AI����
	@Id
	@Column(name = "num")
	private int num;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	
	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "question_list")
	private String questionStr;

	@Column(name = "is_published")
	private boolean published;

	public Quiz() {
		super();
	}

	// �]�� int num �� ai �ͦ� �ҥH�����Ѽƫغc��k
	public Quiz(String name, String description, LocalDate startDate, LocalDate endDate, String questionStr,
			boolean published) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionStr = questionStr;
		this.published = published;
	}
	
	public Quiz(int num, String name, String description, LocalDate startDate, LocalDate endDate, String questionStr,
			boolean published) {
		super();
		this.num = num;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionStr = questionStr;
		this.published = published;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getQuestionStr() {
		return questionStr;
	}

	public void setquestionStr(String questionStr) {
		this.questionStr = questionStr;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}
