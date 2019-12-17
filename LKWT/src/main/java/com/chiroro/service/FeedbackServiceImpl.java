package com.chiroro.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.domain.AnsCommentVO;
import com.chiroro.domain.AnswerAndAnsCommentVO;
import com.chiroro.domain.AnswerVO;
import com.chiroro.domain.QuestionListVO;
import com.chiroro.domain.QuestionVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;
import com.chiroro.mapper.AnsCommentMapper;
import com.chiroro.mapper.AnswerMapper;
import com.chiroro.mapper.QuestionMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j
public class FeedbackServiceImpl implements FeedbackService{
	
	@Setter(onMethod_ = @Autowired)
	private AnsCommentMapper ansCommentMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AnswerMapper answerMapper;
	
	@Setter(onMethod_ = @Autowired)
	private QuestionMapper questionMapper;

	@Override
	public void addQuestion(QuestionVO vo) {
		questionMapper.insert(vo);
		
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

	@Override
	@Transactional(readOnly = true)
	public PageDTO<QuestionListVO> getQuestionList(PagingSource source) {
		
		return new PageDTO<>(source, questionMapper);
	}

	@Override
	@Transactional(readOnly = true)
	public QuestionVO getQuestion(long qno) {
		return questionMapper.selectOne(qno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AnswerAndAnsCommentVO> getAnswerList(long qno) {
		
		return answerMapper.selectJoinedList(qno);
	}

}
