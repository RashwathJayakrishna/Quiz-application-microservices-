package com.jkTech.questionService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jkTech.questionService.dao.QuestionDAO;
import com.jkTech.questionService.model.Question;
import com.jkTech.questionService.model.QuestionWrapper;
import com.jkTech.questionService.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDAO questionDAO;
	public ResponseEntity<List<Question>> getAllQuestion() {
		
		try {
			return new ResponseEntity<> (questionDAO.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList(),HttpStatus.BAD_REQUEST);

	}
	public  ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDAO.save(question);
			return  new ResponseEntity<String>("Success",HttpStatus.CREATED);
		} catch (Exception e) {
			
			e.printStackTrace();
			return  new ResponseEntity<String>("Fail",HttpStatus.BAD_REQUEST);
		}
	}
	public  ResponseEntity<String> addAllQuestion(List<Question> question) {
		 try {
			questionDAO.saveAll(question);
			return  new ResponseEntity<String>("Success",HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResponseEntity<String>("Fail",HttpStatus.BAD_REQUEST);
		}
	}
	public  ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		try {
			return new ResponseEntity<> (questionDAO.findByCategory(category),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList(),HttpStatus.BAD_REQUEST);

	}
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
	(String categoryName, Integer numQuestion) {
	
		List<Integer> questions=questionDAO.findRandomQuestionsByCategory(categoryName,numQuestion);

		return new ResponseEntity<> (questions,HttpStatus.OK);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFormId(List<Integer> questionIds) {
		
		List<QuestionWrapper> wrappers=new ArrayList<>();
		
		List<Question> questions=new ArrayList<>();
		
		for (Integer id : questionIds) {
			questions.add(questionDAO.findById(id).get());
		}
		
		for (Question question : questions) {
			QuestionWrapper questionWrapper= new QuestionWrapper();
			questionWrapper.setId(question.getId());
			questionWrapper.setQuestionTitle(question.getQuestionTitle());
questionWrapper.setOption1(question.getOption1());
questionWrapper.setOption2(question.getOption2());
questionWrapper.setOption3(question.getOption3());
questionWrapper.setOption4(question.getOption4());


		
			
			wrappers.add(questionWrapper);
		}
		return new ResponseEntity<> (wrappers,HttpStatus.OK);
	}
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right=0;
		
		for (Response response : responses) {
			Question question=questionDAO.findById(response.getId()).get();
			if(response.getResponse().equals(question.getQuesrightAnswer())) {
				right++;
				
				
			}
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
