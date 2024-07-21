package com.jkTech.quizServices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jkTech.quizService.dao.QuizDAO;
import com.jkTech.quizService.feign.QuizInterface;
import com.jkTech.quizService.model.QuestionWrapper;
import com.jkTech.quizService.model.Quiz;
import com.jkTech.quizService.model.Response;



@Service
public class QuizService {

	
	@Autowired
	QuizDAO quizDAO;

	@Autowired
	QuizInterface quizInterface;
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		
//		RestTemplate
//		List<Integer> questions=// call the generate url - Resttemplate --http://localhost:8080/question/generate
		
		List<Integer> questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questions);
		quizDAO.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuize(Integer id) {
		
		Optional<Quiz> quiz=quizDAO.findById(id);
		List<Integer> questionFromDB=quiz.get().getQuestionsIds();
		List<QuestionWrapper> questionForUser= quizInterface.getQuestionsFormId(questionFromDB);
//		for (Question q : questionFromDB) {
//			QuestionWrapper questionWrapper=new QuestionWrapper
//					(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//			questionForUser.add(questionWrapper);
//		}
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
	
//		Optional<Quiz> quiz=quizDAO.findById(id);
//		List<Question> questions=quiz.get().getQuestions();
		int right=0;
//		int i=0;
//		for (Response res : response) {
//			if(res.getResponse().equals(questions.get(i).getQuesrightAnswer())) {
//				right++;
//				i++;
//			}
//		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
	
}
