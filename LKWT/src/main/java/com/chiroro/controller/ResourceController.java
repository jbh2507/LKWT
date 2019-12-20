package com.chiroro.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chiroro.domain.AccessLogListVO;
import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.domain.FileVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;
import com.chiroro.service.FileBoxService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/resource")
@Log4j
public class ResourceController {
	
	@Setter(onMethod_ = @Autowired)
	private FileBoxService boxService;

	@GetMapping("/board/{cno}")
	public String GETBoard(PagingSource source, @PathVariable long cno, Model model) {
		
		String userName = "tester";
		
		source.setNo(cno);
		
		PageDTO<FileBoxListVO> pageDTO = boxService.getResourceList(source);
		
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("userName", userName);
		
		return "resource/board";
	}
	
	@GetMapping("/{bno}")
	@ResponseBody
	public ResponseEntity<FileBoxViewVO> GETResource(@PathVariable long bno) {
		FileBoxViewVO result = boxService.getResource(bno);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<Object> POSTResource(FileBoxVO box, String[] fnames) {
		
		FileBoxViewVO vo = new FileBoxViewVO();
		vo.setFilebox(box);
		
		if(fnames != null) {
			List<FileVO> files = new ArrayList<>();
			for (String tmp : fnames) {
				FileVO tmpVO = new FileVO();
				
				int idx = tmp.indexOf('_');
				
				tmpVO.setFname(tmp.substring(idx+1));
				tmpVO.setRegDate(new Date(Long.parseLong(tmp.substring(0, idx))));
				
				files.add(tmpVO);
			}
			vo.setFiles(files);
		}
		
		boxService.addResource(vo);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<Object> PUTResource(FileBoxVO box, String[] fnames) {

		FileBoxViewVO vo = new FileBoxViewVO();
		vo.setFilebox(box);
		
		if(fnames != null) {
			List<FileVO> files = new ArrayList<>();
			for (String tmp : fnames) {
				FileVO tmpVO = new FileVO();
				
				int idx = tmp.indexOf('_');
				
				tmpVO.setBno(box.getBno());
				tmpVO.setFname(tmp.substring(idx+1));
				tmpVO.setRegDate(new Date(Long.parseLong(tmp.substring(0, idx))));
				
				files.add(tmpVO);
			}
			vo.setFiles(files);
		}
		
		boxService.updateResource(vo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{bno}")
	@ResponseBody
	public ResponseEntity<FileBoxViewVO> DELETEResource(@PathVariable long bno) {
		boxService.delete(bno);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/accesslog/{bno}")
	@ResponseBody
	public ResponseEntity<PageDTO<AccessLogListVO>> GETAccesslog(PagingSource source, @PathVariable long bno) {
		source.setNo(bno);
		
		PageDTO<AccessLogListVO> result = boxService.getAccessLog(source);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
