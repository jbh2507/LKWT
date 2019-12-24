package com.chiroro.lkwt_boot.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.service.FileBoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * MainController
 */
@Controller
@RequestMapping("/tnm/*")
@Slf4j
public class MainController {

    @Setter(onMethod_ = @Autowired)
    private FileBoxService service;

    private final String FILE_URL = "localhost:8080/file/download?data=";

    @GetMapping("/main")
    public void tnm(Model model){
        System.out.println("\t TNM");
        model.addAttribute("userName", "tester"+(char)('A'+(int)(Math.random()*21)));
    }

    @GetMapping("/task/board/{cno}")
    public String GETTaskBoard(@PathVariable long cno, @PageableDefault(direction = Direction.DESC, sort = "bno") Pageable pageable, SearchDTO searchDTO, Model model){

        searchDTO.setNo(cno);

        Page<FileBox> result = service.getTaskList(pageable, searchDTO);

        model.addAttribute("pageDTO", result);

        return "tnm/table";
    }

    @GetMapping("/task/{bno}")
    @ResponseBody
    public ResponseEntity<FileBox> GETTask(@PathVariable long bno){

        return new ResponseEntity<>(service.getTask(bno), HttpStatus.OK);
    }


    @GetMapping("/resource/board/{cno}")
    public String GETResourceBoard(@PathVariable long cno, @PageableDefault(direction = Direction.DESC, sort = "bno") Pageable pageable, SearchDTO searchDTO, Model model){
         
        searchDTO.setNo(cno);

        Page<FileBox> result = service.getLibList(pageable, searchDTO);

        model.addAttribute("pageDTO", result);

        return "tnm/table";
    }

    @GetMapping("/resource/{bno}")
    @ResponseBody
    public ResponseEntity<FileBox> GETResorce(@PathVariable long bno){
        

        return new ResponseEntity<>(service.getLib(bno), HttpStatus.OK);
    }

    
    @GetMapping("file/list/{bno}")
    @ResponseBody
    public ResponseEntity<List<File>> GETFileList(@PathVariable long bno){
        List<File> result = service.getFileList(bno);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("task/submit/")
    @ResponseBody
    public void POSTFile(String data){
        File tmpVO = new File();
				
        int idx = data.indexOf('_');
        
        tmpVO.setFname(data.substring(idx+1));
        tmpVO.setRegDate(new Date(Long.parseLong(data.substring(0, idx))));

        service.addSubmission(tmpVO);
    }

    @GetMapping("resource/file/{data}")
    @ResponseBody
    public void GETFile(@PathVariable String data, HttpServletResponse res) {

        int idx = data.indexOf('_');
        long fno = Long.parseLong(data.substring(0, idx));
        
        service.addAccesslog(fno);
        
        try {
            res.sendRedirect(FILE_URL+data);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    
}