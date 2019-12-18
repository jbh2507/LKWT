package com.chiroro.mapper;

import java.util.List;

import com.chiroro.domain.StudentVO;

public interface StudentMapper {
	
	public void insert(StudentVO vo);
	
	public int selectCount(long cno);
	
	public List<String> selectIdList(long cno);

}
