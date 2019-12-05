package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.AnsCommentVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AnsCommentMapperTests {
	
	@Autowired
	private AnsCommentMapper mapper;

	private AnsCommentVO vo;
	
	@Before
	public void ready() {
		vo = new AnsCommentVO();
		vo.setTag("TEST");
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		IntStream.range(63, 82).forEach( i -> {
			if(Math.random() > 0.5) {
				vo.setAno((long)i);
				vo.setComment("comment"+i);
				
				mapper.insert(vo);
			}
		});
	}
}
