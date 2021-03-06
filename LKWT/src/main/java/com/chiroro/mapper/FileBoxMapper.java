package com.chiroro.mapper;

import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.TaskViewVO;

public interface FileBoxMapper extends PagingMapper<FileBoxListVO> {
	
	//c
	public void insert(FileBoxVO vo);
	//r
	public FileBoxViewVO selectOne(long bno);
	
	public TaskViewVO selectTask(long bno);
	//u
	public void update(FileBoxVO vo);
	//d
	public void delete(long bno);
	
	public String isTag(long bno);
	
	
}
