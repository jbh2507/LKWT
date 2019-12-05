package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class QuestionMapperTests {
	
	@Autowired
	private QuestionMapper mapper;
	
	private QuestionVO vo;
	
	@Before
	public void ready() {
		vo = new QuestionVO();
		vo.setCno(1L);
		vo.setCategory("이해도");
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insertTest() {
		
		vo.setContent("test Q");
			
		mapper.insert(vo);
		
	}
	
	@Test
	public void selectTest() {
		long qno = 7L;
		vo = mapper.selectOne(qno);
		
		assertTrue(vo.getQno() == qno);
	}
	
	@Test
	public void selectList() {
		PagingSource source = new PagingSource();
		source.setPage(1);
		source.setAmount(10);
		source.setNo(1L);
		
		List<QuestionListVO> list = mapper.selectList(source);
		
		log.info("====================================");
		log.info(list);
		
		list.stream().forEach(vo -> {
			log.info(vo);
			log.info("==================");	
		});
	}
}
