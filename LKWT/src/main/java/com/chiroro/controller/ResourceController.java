package com.chiroro.controller;

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

import com.chiroro.domain.FileBoxListVO;
import com.chiroro.domain.FileBoxViewVO;
import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;
import com.chiroro.service.FileBoxService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/resource/*")
@Log4j
public class ResourceController {
	
	@Setter(onMethod_ = @Autowired)
	private FileBoxService boxService;

	@GetMapping("/board/{cno}")
	public String GETBoard(PagingSource source, @PathVariable long cno, Model model) {
		source.setNo(cno);
		
		PageDTO<FileBoxListVO> pageDTO = boxService.getResourceList(source);
		
		model.addAttribute("pageDTO", pageDTO);
		
		return "resource/board";
	}
	
	@GetMapping("/{bno}")
	@ResponseBody
	public ResponseEntity<FileBoxViewVO> GETResource(long bno) {
		FileBoxViewVO result = boxService.getResource(bno);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<Object> POSTResource(FileBoxViewVO vo) {
		boxService.addResource(vo);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<Object> PUTResource(FileBoxViewVO vo) {
		boxService.updateResource(vo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{bno}")
	@ResponseBody
	public ResponseEntity<FileBoxViewVO> DELETEResource(@PathVariable long bno) {
		boxService.delete(bno);
		return null;
	}
	
}
