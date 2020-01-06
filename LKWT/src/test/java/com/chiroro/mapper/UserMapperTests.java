package com.chiroro.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.RoleVO;
import com.chiroro.domain.StudentVO;
import com.chiroro.domain.UserDetailVO;
import com.chiroro.domain.UserVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserMapperTests {

	@Autowired
	private UsersMapper mapper;
	
	@Autowired
	private StudentMapper sMapper;
	
	@Autowired RoleMapper rMapper;
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
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
	@Rollback
	public void insert() {
		
		for(int i=0; i<11; i++) {
			String userName = "tester"+(char)('A'+i);
			log.info(userName);
			String pw = bcrypt.encode(userName);
			
			vo.setUserName(userName);
			vo.setPassword(pw);
			
			mapper.insert(vo);
			
			StudentVO svo = new StudentVO();
			
			svo.setCno(1L);
			svo.setUserName(userName);
			
			sMapper.insert(svo);
			
			RoleVO role = new RoleVO();
			role.setAuthority("ROLE_STUDENT");
			role.setUserName(userName);
			
			rMapper.insert(role);
			
		}
	}
	
	@Test
	public void selectDetail() {
		UserDetailVO userDetail = mapper.selectDetail("tester");
		log.info(userDetail);
		
		
	}
}
