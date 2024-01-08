package com.example.quiz.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz.entity.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizReq {
	
		private String name;
		
		private String description;
		
		@JsonProperty("start_date")
		private LocalDate startDate;
		
		@JsonProperty("end_date")
		private LocalDate endDate;
		
		@JsonProperty("question_list")
		private List<Question> questionList;
		
		@JsonProperty("is_published")
		private boolean published;

		public QuizReq() {
			super();
			// TODO Auto-generated constructor stub
		}

		public QuizReq(String name, String description, LocalDate startDate, LocalDate endDate,
				List<Question> questionList, boolean published) {
			super();
			this.name = name;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.questionList = questionList;
			this.published = published;
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

		public List<Question> getQuestionList() {
			return questionList;
		}

		public void setQuestionList(List<Question> questionList) {
			this.questionList = questionList;
		}

		public boolean isPublished() {
			return published;
		}

		public void setPublished(boolean published) {
			this.published = published;
		}

	}

