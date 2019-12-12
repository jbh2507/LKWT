package com.chiroro.mapper;

import org.apache.ibatis.annotations.Select;

import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;

public interface QuestionMapper extends PagingMapper<QuestionListVO>{
	
	//c
	public void insert(QuestionVO vo);
	//r
	public QuestionVO selectOne(long qno);
	
	//마지막으로 들어간 id갑 확인
	@Select("SELECT LAST_INSERT_ID()")
	public long lastAI();
	
}
