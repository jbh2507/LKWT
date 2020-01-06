package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.domain.AnsCommentVO;
import com.chiroro.domain.AnswerDataVO;
import com.chiroro.domain.AnswerVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.DataSearchDTO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class QuestionMapperTests {
	
	@Autowired
	private QuestionMapper mapper;
	
	@Autowired
	private AnswerMapper amapper;
	
	@Autowired
	private AnsCommentMapper cmapper;
	
	private QuestionVO vo;
	
	@Before
	public void ready() {
		vo = new QuestionVO();
		vo.setCno(1L);
		String category = "진행도";
		if(Math.random() > 0.5) category = "이해도";
		vo.setCategory(category);
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insertTest() {
		
		IntStream.range(0, 201).forEach(i->{
			log.info(""+i);
			vo.setContent("test Q: "+i);
			mapper.insert(vo);
			
			
			PagingSource source = new PagingSource();
			source.setNo(1L);
			
			long qno = mapper.selectList(source).get(0).getQuestion().getQno();
			AnswerVO avo = new AnswerVO();
			for(int j=0; j<11; j++) {
				String userName = "tester"+(char)('A'+j);
				avo.setQno(qno);
				avo.setUsername(userName);
				avo.setIndicator((int)(Math.random()*5)*25);
				
				amapper.insert(avo);
				
				if(Math.random() < 0.2) {
					AnsCommentVO cvo = new AnsCommentVO();
					cvo.setTag("TEST");
					cvo.setComment("it is test answer comment");
					cmapper.insert(cvo);
				}
				
			}
			
			
			
		});
		
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
	
	@Test
	public void selectDataTest() {
		DataSearchDTO dDto = new DataSearchDTO();
		dDto.setUserName("testeacher");
		dDto.setCategory("cno");
		dDto.setKeyword("1");
		List<AnswerDataVO> result = mapper.selectData(dDto);
		
		result.forEach(log::info);
	}
}
