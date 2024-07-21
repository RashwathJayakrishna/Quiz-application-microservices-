package com.jkTech.quizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jkTech.quizService.model.QuestionWrapper;
import com.jkTech.quizService.model.Response;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
	
	//generate
	@GetMapping("question/generate")
		public ResponseEntity<List<Integer>>getQuestionsForQuiz
		(@RequestParam String categoryName,@RequestParam Integer numQuestion);
	//getQuestion (question id)
	@PostMapping("question/getQuestiuon")
	public List<QuestionWrapper> getQuestionsFormId
	(@RequestBody List<Integer> questionIds);
	//getscore
	@PostMapping("question/getScore")
		public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses);
	

}
