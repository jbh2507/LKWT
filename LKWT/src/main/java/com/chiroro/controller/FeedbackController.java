package com.chiroro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;
import com.chiroro.service.FeedbackService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("feedback/*")
@Log4j
public class FeedbackController {
	
	@Setter(onMethod_ = @Autowired)
	private FeedbackService service;
	
	// question List
	@GetMapping("/board")
	public void GETFeedback(PagingSource source, Model model) {
		log.info(source + "\t controller IN");
		
		PageDTO<QuestionListVO> pageDTO = service.getQuestionList(source);
		model.addAttribute("pageDTO",pageDTO);
		log.info("\tpageDTO: "+pageDTO);
		
		
	}
	
	// question
	@GetMapping("/question/{qno}")
	@ResponseBody
	public ResponseEntity<QuestionVO> GETQuestion(@PathVariable Integer qno){
		log.info(qno+"\tREST GET question");
		
		QuestionVO result = service.getQuestion(qno);
		return new ResponseEntity<QuestionVO>(result, HttpStatus.OK);
	}
	
	// answerList
	@GetMapping("/question/{qno}/answerList")
	@ResponseBody
	public ResponseEntity<List<AnswerAndAnsCommentVO>> GETAnswerList(@PathVariable Integer qno){
		log.info(qno+"\tREST GET answerList");
		List<AnswerAndAnsCommentVO> result = service.getAnswerList(qno);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
