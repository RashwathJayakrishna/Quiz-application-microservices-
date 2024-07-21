package com.jkTech.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkTech.quizService.model.QuestionWrapper;
import com.jkTech.quizService.model.QuizDto;
import com.jkTech.quizService.model.Response;
import com.jkTech.quizServices.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizeController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String>createQuize
	(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
	}
	
	@GetMapping("get/{id}")
	
	public ResponseEntity<List<QuestionWrapper>>getQuize(@PathVariable Integer id){
		return quizService.getQuize(id);
	}
	
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response>response){
		
		
		return quizService.calculateResult(id,response);
		
	}
}
