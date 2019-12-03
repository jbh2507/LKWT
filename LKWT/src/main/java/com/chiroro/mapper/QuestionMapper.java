package com.chiroro.mapper;

import com.chiroro.domain.QuestionVO;

public interface QuestionMapper {
	
	//c
	public void insertQuestion(QuestionVO vo);
	//r
	public void selectQuestion(long qno);
	
}
