package com.chiroro.mapper;

import java.util.List;

import com.chiroro.domain.FileListVO;
import com.chiroro.domain.FileVO;

public interface FileMapper{

	//c
	public void insert(FileVO vo);
	
	//r
	public FileVO selectOne(long fno);
	
	public List<FileListVO> selectList(long bno);
	
	public List<FileListVO> selectListInTask(long bno, String userName);
	
	//d
	public void delete(long fno);
}
