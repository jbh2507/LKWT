package com.chiroro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.QuestionVO;

public interface ApiService {
	//피드백요청
	public long addQuestion(QuestionVO vo);
		
	//피드백응답
	public void addAnswer(AnswerAndAnsCommentVO vo);
}
