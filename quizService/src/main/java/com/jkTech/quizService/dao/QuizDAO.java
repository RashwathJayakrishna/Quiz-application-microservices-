package com.jkTech.quizService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jkTech.quizService.model.Quiz;


public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
