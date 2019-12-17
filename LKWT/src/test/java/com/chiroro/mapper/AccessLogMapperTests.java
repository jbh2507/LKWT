package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.AccessLogListVO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AccessLogMapperTests {

	@Autowired
	private AccessLogMapper mapper;
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void selectList() {
		PagingSource source = new PagingSource();
		source.setNo(50L);
		
		List<AccessLogListVO> list = mapper.selectList(source);
		
		log.info("===============\tselectList result");
		list.forEach(log::info);
	}
	
	@Test
	public void selectTotal() {
		PagingSource source = new PagingSource();
		source.setNo(50L);
		
		long total = mapper.selectTotal(source);
		
		log.info(total+"\t selectTotal result");
	}
}
