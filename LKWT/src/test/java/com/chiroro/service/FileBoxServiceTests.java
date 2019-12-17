package com.chiroro.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.AccessLogListVO;
import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.FileVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FileBoxServiceTests {

	@Autowired
	private FileBoxService service;
	
	private PagingSource source;
	private FileBoxVO box;
	private FileBoxViewVO boxView;
	private FileVO file;
	
	@Before
	public void ready() {
		source = new PagingSource();
		
		box = new FileBoxVO();
		box.setCno(1L);
		box.setContent("test "+System.currentTimeMillis());
		
		boxView = new FileBoxViewVO();
		
		file = new FileVO();
		file.setFname("testfile"+(int)(Math.random()*100)+".txt");
	}
	
	@Test
	public void isExist() {
		assertNotNull(service);
	}
	
	@Test
	public void addTaskTest() {
		box.setTitle("addTaskTest");

		service.addTask(box);
	}
	
	@Test
	public void getTask() {
		FileBoxViewVO task = service.getTask(1L);
		
		log.info(task+"\tgetTask result");
	}
	
	@Test
	public void getTaskListTest() {
		source.setNo(1L);
		PageDTO<FileBoxListVO> dto = service.getTaskList(source);
		
		assertNotNull(dto);
		
		assertTrue(dto.getStart() == 1);

		List<FileBoxListVO> list = dto.getList();
		
		assertNotNull(list);
		
		log.info("===============\tgetTaskList result");
		list.forEach(item->{
			log.info(item);
		});
	}
	
	@Test
	public void addRRTest() {

		box.setTitle("illegalTagInsetTest");
		box.setTag('T');
		
		boxView.setFilebox(box);
		
		FileVO file2 = new FileVO();
		file2.setBno(0L);
		file2.setFname("illegalBnoInsetTestFile.txt");
		
		List<FileVO> list = new ArrayList<>();
		list.add(file);
		list.add(file2);
		
		boxView.setFiles(list);
		
		service.addResourceRoom(boxView);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getRRTest() {
		service.getResourceRoom(1L);
	}
	
	@Test
	public void getLogTest() {
		source.setNo(50L);
		PageDTO<AccessLogListVO> dto = service.getAccessLog(source);
		
		log.info("===============\tgetLogList result");
		dto.getList().forEach(log::info);
	}
	
}
