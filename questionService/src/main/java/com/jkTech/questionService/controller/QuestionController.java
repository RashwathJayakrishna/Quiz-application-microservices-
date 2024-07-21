

package com.jkTech.questionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkTech.questionService.model.Question;
import com.jkTech.questionService.model.QuestionWrapper;
import com.jkTech.questionService.model.Response;
import com.jkTech.questionService.service.QuestionService;



@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		
		return questionService.getAllQuestion();
		
	}
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
	
		return  questionService.addQuestion(question);
	}
	@PostMapping("addAllQuestions")
	public  ResponseEntity<String> addAllQuestion(@RequestBody List<Question> question) {
	
		return  questionService.addAllQuestion(question);
	}
	
	/*@RequestParam
	 * http://localhost:8080/question/getQuestionsByCategory?category=SQL
	 * @GetMapping("getQuestionsByCategory") private List<Question>
	 * getQuestionsByCategory(@RequestParam String category) {
	 */	
	
	
	@GetMapping("getQuestionsByCategory/{category}") 
	private  ResponseEntity<List<Question>>getQuestionsByCategory(@PathVariable String category) {
		
	return  questionService.getQuestionsByCategory(category);
	}
	
	//generate
	@GetMapping("generate")
		public ResponseEntity<List<Integer>>getQuestionsForQuiz
		(@RequestParam String categoryName,@RequestParam Integer numQuestion){
			
	return questionService.getQuestionsForQuiz(categoryName,numQuestion);
	}
	//getQuestion (question id)
	@PostMapping("getQuestiuon")
	public ResponseEntity<List<QuestionWrapper>>getQuestionsFormId
	(@RequestBody List<Integer> questionIds){
		
		System.out.println(environment.getProperty("local.server.port"));
return questionService.getQuestionsFormId(questionIds);
}
	//getscore
	@PostMapping("getScore")
		public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses){
			
		return questionService.getScore(responses);

	}
	
}
