package com.chiroro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chiroro.domain.AnswerDataVO;
import com.chiroro.domain.ClassVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.domain.UserDetailVO;
import com.chiroro.dto.DataSearchDTO;
import com.chiroro.service.FeedbackService;

import lombok.Setter;

@Controller
@RequestMapping("/datacenter")
public class DataCenterController {
	
	@Setter(onMethod_ = @Autowired)
	private FeedbackService feedService;
	
	@GetMapping()
	public String GETDataCenter(Model model) {
		Authentication authen = SecurityContextHolder.getContext().getAuthentication();
		List<ClassVO> classList = ((UserDetailVO)authen.getPrincipal()).getLectures();
		model.addAttribute("lectures", classList);
		
		return "/datacenter/center";
	}
	
	@GetMapping("/getData")
	@ResponseBody
	public ResponseEntity<List<AnswerDataVO>> GETQuestionList(DataSearchDTO dto){
	
		
		Authentication authen = SecurityContextHolder.getContext().getAuthentication();
		String userName = ((UserDetailVO)authen.getPrincipal()).getUsername();
		dto.setUserName(userName);
		
		
		List<AnswerDataVO> result = feedService.getQuestionData(dto);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/question/{qno}")
	@ResponseBody
	public ResponseEntity<QuestionVO> GETQuestion(@PathVariable Integer qno){
		
		QuestionVO result = feedService.getQuestion(qno);
		return new ResponseEntity<QuestionVO>(result, HttpStatus.OK);
	}
	
	
}
