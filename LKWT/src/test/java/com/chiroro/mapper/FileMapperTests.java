package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.FileListVO;
import com.chiroro.domain.FileVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FileMapperTests {
	
	@Autowired
	private FileMapper mapper;
	
	private FileVO vo;
	
	@Before
	public void ready() {
		vo = new FileVO();
		vo.setFname("testfile.txt");
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	 
	@Test
	public void insert() {
		IntStream.range(0, 3).forEach(i->{
			vo.setBno(30L);

			mapper.insert(vo);
		});
	}
	
	@Test
	public void selectOne() {
		FileVO file = mapper.selectOne(1L);
		
		log.info(file+"\t selectOne result");
	}
	
	@Test
	public void selectList() {
		List<FileListVO> list =mapper.selectList(50L);
		
		log.info("===============\tselectList result");
		list.forEach(log::info);
	}
}
