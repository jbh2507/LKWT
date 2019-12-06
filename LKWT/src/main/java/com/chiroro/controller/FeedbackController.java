package com.chiroro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

@RestController
@RequestMapping("/feedback/*")
public class FeedbackController {

	// question List
	@GetMapping("/{cno}")
	public ResponseEntity<PageDTO<QuestionListVO>> GETFeedback(@PathVariable("cno") Integer cno, @RequestParam PagingSource source) {
		source.setNo((long)cno);
		
		return null;
	}
	
	// question
	@GetMapping("/question/{qno}")
	public ResponseEntity<List<AnswerAndAnsCommentVO>> GETQuestion(@PathVariable Integer qno){
		
		return null;
	}
	
	// answerList
	@GetMapping("/question/{qno}/answerList")
	public ResponseEntity<List<AnswerAndAnsCommentVO>> GETAnswerList(@PathVariable Integer qno){
		
		return null;
	}
	
	
}
