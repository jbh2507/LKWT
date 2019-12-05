package com.chiroro.mapper;

import java.util.List;

import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.AnswerVO;

public interface AnswerMapper extends PagingMapper<AnswerVO>{
	//c
	public void insert(AnswerVO vo);
	//r
	public AnswerVO selectOne(long ano);
	//rl
	
	//joined
	public List<AnswerAndAnsCommentVO> selectJoinedList(long qno);
	
	//isExit
	public Boolean isExist(AnswerVO qnoAndUsername);
}
