package com.chiroro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiroro.mapper.StudentMapper;

import lombok.Setter;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Setter(onMethod_ = @Autowired)
	private StudentMapper studentMapper;

	@Override
	public List<String> getIdList(long cno) {
		
		return studentMapper.selectIdList(cno);
	}

	@Override
	public int getCount(long cno) {
		return studentMapper.selectCount(cno);
	}

}
