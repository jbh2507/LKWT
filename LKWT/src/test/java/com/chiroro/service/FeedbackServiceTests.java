package com.chiroro.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chiroro.domain.AnsCommentVO;
import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.AnswerVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FeedbackServiceTests {

	@Autowired
	private FeedbackService service;
	
	@Test
	public void isExist() {
		assertNotNull(service);
	}
	
	@Test
	public void addAnswer1() {
		
		AnswerAndAnsCommentVO vo = new AnswerAndAnsCommentVO();
		

		AnswerVO answer = new AnswerVO();
		answer.setQno(297L);
		answer.setIndicator(100);
		answer.setUsername("tester");
		
		vo.setAnswer(answer);
		
		service.addAnswer(vo);
	}
	
	@Test
	public void addAnswer2() {
		
		AnswerAndAnsCommentVO vo = new AnswerAndAnsCommentVO();
		

		AnswerVO answer = new AnswerVO();
		answer.setQno(296L);
		answer.setIndicator(100);
		answer.setUsername("tester");
		vo.setAnswer(answer);
		
		AnsCommentVO ansComment = new AnsCommentVO();
		ansComment.setComment("test comment");
		
		vo.setAnsComment(ansComment);
		
		service.addAnswer(vo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addAnswer3() {
		
		AnswerAndAnsCommentVO vo = new AnswerAndAnsCommentVO();
		

		AnswerVO answer = new AnswerVO();
		answer.setQno(300L);
		answer.setIndicator(44);
		answer.setUsername("tester");
		vo.setAnswer(answer);
		
		service.addAnswer(vo);
	}
	
//	@Test
//	public void addAnswerTransaction() {
//		
//	}
	
	@Test
	public void getQuestionList() {
		PagingSource source = new PagingSource();
		source.setNo(1L);
		PageDTO<QuestionListVO> page = service.getQuestionList(source);
		
		log.info(page);
		log.info("=====================================");
		page.getList().forEach(log::info);
	}
	
	
	/*
	 * //피드백요청
	public void addQuestion(QuestionVO vo);
	
	//피드백응답
	public void addAnswer(AnswerAndAnsCommentVO vo);
	
	//피드백 목록
	public PageDTO<QuestionListVO> getQuestionList(PagingSource source);
	
	//피드백 가져오기
	public QuestionVO getQuestion(long qno);
	
	//피드백 응답 가져오기
	public List<AnswerAndAnsCommentVO> getAnswerList(long qno);
	 */
}
