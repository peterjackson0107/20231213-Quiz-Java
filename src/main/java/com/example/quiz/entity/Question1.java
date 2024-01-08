package com.example.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "question")
@IdClass(value = Question1Id.class)   // 連結 QuestionId
public class Question1 {

	@Id
	@Column(name = "num")
	private int num;

	@Id
	@Column(name = "quiz_num")
	private int quizNum;

	@Column(name = "title")
	private String title;

	@Column(name = "type")
	private String type;

	@Column(name = "is_necessary")
	private boolean necessary;

	@Column(name = "options")
	private String options;

	@Column(name = "is_published")
	// 須把 is 拿掉 不然資料庫接不回來 ( 因boolean get / set 時會自動產生 is )
	private boolean published;

	public Question1() {
		super();
	}

	public Question1(int num, int quizNum, String title, String type, boolean necessary, String options,
			boolean published) {
		super();
		this.num = num;
		this.quizNum = quizNum;
		this.title = title;
		this.type = type;
		this.necessary = necessary;
		this.options = options;
		this.published = published;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(int quizNum) {
		this.quizNum = quizNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

	public String isOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}
