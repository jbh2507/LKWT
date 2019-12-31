package com.chiroro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.chiroro.domain.AnswerDataVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.DataSearchDTO;

public interface QuestionMapper extends PagingMapper<QuestionListVO>{
	
	//c
	public void insert(QuestionVO vo);
	//r
	public QuestionVO selectOne(long qno);
	
	public List<AnswerDataVO> selectData(DataSearchDTO dto);
	
	//마지막으로 들어간 id갑 확인
	@Select("SELECT LAST_INSERT_ID()")
	public long lastAI();
	
}
