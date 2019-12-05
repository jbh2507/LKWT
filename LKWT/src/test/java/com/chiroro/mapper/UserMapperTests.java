package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.UserVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserMapperTests {

	@Autowired
	private UsersMapper mapper;
	
	private UserVO vo;
	
	@Before
	public void ready() {
		vo = new UserVO();
	}
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		IntStream.range(0, 20).forEach(i -> {
			vo.setUserName("tester"+(char)('A'+i%20));
			vo.setPassword(vo.getUserName());
			
			mapper.insert(vo);
			log.info(vo+"\t inserted vo");
		});
	}
}