package com.chiroro.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.domain.AnsCommentVO;
import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.AnswerVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.mapper.AnsCommentMapper;
import com.chiroro.mapper.AnswerMapper;
import com.chiroro.mapper.QuestionMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class ApiServiceImpl implements ApiService {
	
	@Setter(onMethod_ = @Autowired)
	private QuestionMapper questionMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AnswerMapper answerMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AnsCommentMapper ansCommentMapper;
	
	@Override
	public long addQuestion(QuestionVO vo) {
		questionMapper.insert(vo);
		
		
		return questionMapper.lastAI();
	}

	@Override
	public void addAnswer(AnswerAndAnsCommentVO vo) {
		AnswerVO answer = vo.getAnswer();
		AnsCommentVO comment = vo.getAnsComment();
		
		// 해당 유저의 답변이 이미 있는 경우 체크
		if(answerMapper.isExist(answer)) {
			log.warn("insertAnswerERROR: answer is already exist.\ninfo:"+vo);
			
			throw new IllegalArgumentException(new SQLException("that user's answer is already exist"));
		}
		
		answerMapper.insert(answer);
		if(comment != null && (comment.getComment() != null || comment.getTag() != null)) {
			ansCommentMapper.insert(comment);
		}
	}

}
