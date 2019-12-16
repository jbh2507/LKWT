package com.chiroro.service;

import com.chiroro.domain.AccessLogVO;
import com.chiroro.domain.ResourceRoomVO;
import com.chiroro.domain.TaskListVO;
import com.chiroro.domain.TaskVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

public interface FileBoxService {
	
	//과제 등록
	public void addTask(TaskVO vo);
	//과제 리스트
	public PageDTO<TaskListVO> getTaskList(PagingSource source);
	//과제 조회
	public TaskVO getTask(long bno);
	
	//자료 등록
	public void addResourceRoom(ResourceRoomVO box);
	//자료 리스트
	public PageDTO<ResourceRoomVO> getResourceRoomList(PagingSource source);
	//자료 조회
	public ResourceRoomVO getResourceRoom(long bno);
	
	//엑세스 로그 조회
	public AccessLogVO getAccessLog(PagingSource source);

}
