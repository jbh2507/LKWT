package com.chiroro.service;

import org.springframework.stereotype.Service;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

@Service
public interface FeedbackService {
	//피드백요청
	public void addQuestion(QuestionVO vo);
	
	//피드백응답
	public void addAnswer(AnswerAndAnsCommentVO vo);
	
	//피드백 목록
	public PageDTO<QuestionListVO> getQuestionList(PagingSource source);
	
	//피드백 가져오기
	public QuestionVO getQuestion();
	
	//피드백 응답 가져오기
	public PageDTO<?> getAnswerList(PagingSource source);
}
