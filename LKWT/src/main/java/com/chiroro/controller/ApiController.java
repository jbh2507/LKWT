package com.chiroro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.service.ApiService;
import com.chiroro.service.FeedbackService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ApiController {
	
	@Setter(onMethod_ = @Autowired)
	private ApiService service;
	
	@PostMapping("/answer")
	public ResponseEntity<Object> POSTAnswer(AnswerAndAnsCommentVO vo){
		
		log.info(vo+"\t ApiController POSTAnswer");
		
		try {
			service.addAnswer(vo);
		} catch (Exception e) {
			log.warn(e);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/question")
	public ResponseEntity<Long> POSTQuestion(QuestionVO vo){
		
		log.info(vo+"\t ApiController POSTQuestion");
		
		long id = -1L;
		try {
			id = service.addQuestion(vo);
		} catch (Exception e) {
			log.warn(e);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(id ,HttpStatus.CREATED);
	}
}
