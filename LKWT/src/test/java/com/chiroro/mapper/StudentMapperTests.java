package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.StudentVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class StudentMapperTests {
	
	@Autowired
	private StudentMapper mapper;
	
	private StudentVO vo;
	
	@Before
	public void ready() {
		vo = new StudentVO();
		vo.setCno(1L);
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		IntStream.range(0, 20).forEach(i -> {
			vo.setUserName("tester"+(char)('A'+i%20));
			vo.setName("TO"+(char)('A'+i%20));
			
			mapper.insert(vo);
			log.info(vo+"\t inserted vo");
		});
	}
}
