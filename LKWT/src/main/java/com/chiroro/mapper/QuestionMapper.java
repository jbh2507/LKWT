package com.chiroro.mapper;

import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;

public interface QuestionMapper extends PagingMapper<QuestionListVO>{
	
	//c
	public void insert(QuestionVO vo);
	//r
	public QuestionVO selectOne(long qno);
	
	
	
}
