package com.chiroro.lkwt_boot.service;

import java.util.List;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.dto.FileDTO;
import com.chiroro.lkwt_boot.dto.SearchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * FileBoxService
 */
public interface FileBoxService {
    //과제 제출
    //  과제 리스트
    public Page<FileBox> getTaskList(Pageable page, SearchDTO dto);
    //  과제 열람
    public FileBox getTask(long bno);
    //  과제 제출
    public boolean addSubmission(FileDTO file);
    //  과제 냇나 확인
    public boolean isSubmited(long bno);

    //자료 확인
    //  자료 리스트
    public Page<FileBox> getLibList(Pageable page, SearchDTO dto);
    //  자료 열람
    public FileBox getLib(long bno);

    //파일 목록
    public List<File> getFileList(long bno);

    //엑세스 로그
    public void addAccesslog(long fno);
}