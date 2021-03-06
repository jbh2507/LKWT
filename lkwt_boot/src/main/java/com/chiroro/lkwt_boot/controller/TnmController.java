package com.chiroro.lkwt_boot.controller;

import java.sql.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.domain.User;
import com.chiroro.lkwt_boot.dto.FileDTO;
import com.chiroro.lkwt_boot.dto.ResourceDTO;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.dto.TaskDTO;
import com.chiroro.lkwt_boot.service.FileBoxService;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.collections.Enumerator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * MainController
 */
@Controller
@RequestMapping("/tnm/*")
@Slf4j
public class TnmController {

    @Setter(onMethod_ = @Autowired)
    private FileBoxService service;

    private final String FILE_URL = "http://10.10.10.132:8080/";

    @GetMapping("/main")
    public void tnm(Model model){
        
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User)authen.getPrincipal();
        
        String userName = curUser.getUsername();
        String lecName = curUser.getLectures().iterator().next().getCname();

        model.addAttribute("userName", userName);
        model.addAttribute("className", lecName);
    }

    @GetMapping("/task/board")
    public String GETTaskBoard(@PageableDefault(direction = Direction.DESC, sort = "bno") Pageable pageable, SearchDTO searchDTO, Model model){

        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User)authen.getPrincipal();
        long cno = curUser.getLectures().iterator().next().getCno();

        searchDTO.setNo(cno);

        Page<FileBox> result = service.getTaskList(pageable, searchDTO);

        model.addAttribute("userName", curUser.getUsername());

        model.addAttribute("pageDTO", result);
        model.addAttribute("tag", 'T');
        model.addAttribute("fileServer", FILE_URL);

        return "tnm/table";
    }

    @GetMapping("/task/{bno}")
    @ResponseBody
    public ResponseEntity<TaskDTO> GETTask(@PathVariable long bno){
        TaskDTO result = new TaskDTO();
 
        FileBox box = service.getTask(bno);
        result.setFileBox(box);
        result.setSubmited(service.isSubmited(bno));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/resource/board")
    public String GETResourceBoard(@PageableDefault(direction = Direction.DESC, sort = "bno") Pageable pageable, SearchDTO searchDTO, Model model){
        
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User)authen.getPrincipal();
        long cno = curUser.getLectures().iterator().next().getCno();

        searchDTO.setNo(cno);

        Page<FileBox> result = service.getTaskList(pageable, searchDTO);

        model.addAttribute("userName", curUser.getUsername());

        model.addAttribute("pageDTO", result);
        model.addAttribute("tag", 'L');
        model.addAttribute("fileServer", FILE_URL);

        return "tnm/table";
    }

    @GetMapping("/resource/{bno}")
    @ResponseBody
    public ResponseEntity<ResourceDTO> GETResorce(@PathVariable long bno){
        ResourceDTO result = new ResourceDTO();
        
        FileBox box = service.getLib(bno);
        result.setFileBox(box);
        result.setFiles(box.getFiles());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    @GetMapping("/file/list/{bno}")
    @ResponseBody
    public ResponseEntity<List<File>> GETFileList(@PathVariable long bno){
        List<File> result = service.getFileList(bno);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/task/submit")
    @ResponseBody
    public void POSTFile(FileDTO dto){
    
        service.addSubmission(dto);
    }

    @GetMapping("/resource/file/{data}")
    @ResponseBody
    public void GETFile(@PathVariable String data, HttpServletResponse res) {

        int idx = data.indexOf('_');
        long fno = Long.parseLong(data.substring(0, idx));
        
        service.addAccesslog(fno);
        
        try {
            res.sendRedirect(FILE_URL+"download?data="+data);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    
}