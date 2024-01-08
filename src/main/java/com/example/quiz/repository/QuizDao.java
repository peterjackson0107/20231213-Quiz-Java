package com.example.quiz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

	public int deleteByNumIn(List<Integer> numList);

	// Containingģ���Ҍ�
	public List<Quiz> findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String name, LocalDate startDate, LocalDate endDate);

	// orderby������
	public List<Quiz> findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualOrderByEndDate(String name, LocalDate startDate,
			LocalDate endDate);

	public List<Quiz> findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndPublishedTrue(String name, LocalDate startDate,
			LocalDate endDate);

}
