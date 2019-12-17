package com.chiroro.service;

import com.chiroro.domain.AccessLogListVO;
import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.FileVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

public interface FileBoxService {
	
	//과제 등록
	public void addTask(FileBoxVO vo);
	//과제 리스트
	public PageDTO<FileBoxListVO> getTaskList(PagingSource source);
	//과제 조회
	public FileBoxViewVO getTask(long bno);
	
	//자료 등록
	public void addResourceRoom(FileBoxViewVO vo);
	//자료 리스트
	public PageDTO<FileBoxListVO> getResourceRoomList(PagingSource source);
	//자료 조회
	public FileBoxViewVO getResourceRoom(long bno);
	
	//파일 등록
	public void addFile(FileVO vo);
	
	//엑세스 로그 조회
	public PageDTO<AccessLogListVO> getAccessLog(PagingSource source);

}
