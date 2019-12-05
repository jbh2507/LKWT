package com.chiroro.mapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		vo.setQno(300L);
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		//LastInsert id
	}
	
	@Test
	public void selectJoinedList() {
		List<?> list = mapper.selectJoinedList(300L);
		
		list.forEach(log::info);
	}
	
	@Test
	public void checkExist() {
		AnswerVO ans = mapper.selectOne(3);
		
		ans.setIndicator(44);
		
		boolean bool = mapper.isExist(ans);
		log.info(bool+"\t isExist True");
		assertTrue(bool);
		
		ans.setUsername("$fakeUser");
		
		bool = mapper.isExist(ans);
		
		log.info(bool+"\t isExist False");
		
		assertFalse(bool);
	}
	
}
