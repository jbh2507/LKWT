package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.AnswerVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AnswerMapperTests {
	
	@Autowired
	private AnswerMapper mapper;

	private AnswerVO vo;
	
	@Before
	public void ready() {
		vo = new AnswerVO();
		vo.setQno(1L);
		vo.setUsername("tester");
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		for(int i=0; i<31; i++) {
			vo.setIndicator(25*(int)(Math.random()*5));
			
			mapper.insert(vo);
		}
	}
}
