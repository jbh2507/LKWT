package com.chiroro.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.domain.AccessLogListVO;
import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.FileVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;
import com.chiroro.mapper.AccessLogMapper;
import com.chiroro.mapper.FileBoxMapper;
import com.chiroro.mapper.FileMapper;

import lombok.Setter;
import oracle.net.aso.e;

@Service
@Transactional
public class FileBoxServiceImpl implements FileBoxService {
	
	@Setter(onMethod_ = @Autowired)
	private FileBoxMapper fileboxMapper;
	
	@Setter(onMethod_ = @Autowired)
	private FileMapper fileMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AccessLogMapper logMapper;

	@Override
	public void addTask(FileBoxVO vo) {
		vo.setTag('T');
		fileboxMapper.insert(vo);
	}

	@Override
	@Transactional(readOnly = true)
	public PageDTO<FileBoxListVO> getTaskList(PagingSource source) {
		source.setTag("T");
		
		return new PageDTO<>(source, fileboxMapper);
	}

	@Override
	@Transactional(readOnly = true)
	public FileBoxViewVO getTask(long bno) {
		FileBoxViewVO view = fileboxMapper.selectOne(bno);
		if(view.getFilebox().getTag() != 'T') throw new IllegalArgumentException("bno: "+bno+" is not Task's bno");
		
		return fileboxMapper.selectOne(bno);
	}

	@Override
	public void addResourceRoom(FileBoxViewVO vo) {
		FileBoxVO box = vo.getFilebox();
		box.setTag('L');
		List<FileVO> files = vo.getFiles();
		files.forEach(e -> e.setBno(null));
		
		fileboxMapper.insert(box);
		files.forEach(fileMapper::insert);
	}

	@Override
	@Transactional(readOnly = true)
	public PageDTO<FileBoxListVO> getResourceRoomList(PagingSource source) {
		source.setTag("L");
		
		return new PageDTO<>(source, fileboxMapper);
	}

	@Override
	@Transactional(readOnly = true)
	public FileBoxViewVO getResourceRoom(long bno) {
		FileBoxViewVO view = fileboxMapper.selectOne(bno);
		if(view.getFilebox().getTag() != 'L') throw new IllegalArgumentException("bno: "+bno+" is not ResourceRoom's bno");
		
		return fileboxMapper.selectOne(bno);
	}

	@Override
	public void addFile(FileVO vo) {
		fileMapper.insert(vo);
	}

	@Override
	@Transactional(readOnly = true)
	public PageDTO<AccessLogListVO> getAccessLog(PagingSource source) {

		return new PageDTO<>(source, logMapper);
	}

}
