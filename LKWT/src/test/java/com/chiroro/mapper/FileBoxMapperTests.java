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

import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.TaskViewVO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FileBoxMapperTests {

	@Autowired
	private FileBoxMapper mapper;
	
	private FileBoxVO vo;
	private PagingSource source;
	
	@Before
	public void ready() {
		vo = new FileBoxVO();
		vo.setCno(1L);
		vo.setContent("forTest now: "+System.currentTimeMillis());
		
		source = new PagingSource();
		source.setNo(1L);
		source.setAmount(10);
	}
	
	
	@Test
	public void isExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insert() {
		
		IntStream.range(0, 10).forEach(i ->{
			vo.setTitle("test "+i);
			
			char tag = 'T';
			if(Math.random() < 0.5) tag = 'L';
			vo.setTag(tag);
			
			mapper.insert(vo);
		});
		
	}
	
	@Test
	public void selectOne() {
		FileBoxViewVO f = mapper.selectOne(1L);
		log.info(f+"\t selectOne result");
	}
	
	@Test
	public void selectList(){
		source.setTag("T");
		source.setPage(1);
		source.setCategory("W");
		source.setKeyword("5");
		
		List<FileBoxListVO> list = mapper.selectList(source);
		
		log.info("==============\tselectList");
		list.forEach(log::info);
		log.info("==============");
	}
	
	@Test
	public void selectTotal() {
		source.setTag("L");
		long total = mapper.selectTotal(source);
		
		log.info(total+"\t selectTotal result");
	}
	
	@Test
	public void selectTask() {
		TaskViewVO result = mapper.selectTask(22L);
		
		log.info(result);
	}
	
}
