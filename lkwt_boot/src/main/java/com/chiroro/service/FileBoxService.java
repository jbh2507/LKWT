package com.chiroro.service;

import java.util.Optional;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
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
    public boolean addSubmission(File file);
    //  과제 수정
    public boolean updateSubmission(File file);

    //자료 확인
    //  자료 리스트
    public Page<FileBox> getLibList(Pageable page, SearchDTO dto);
    //  자료 열람
    public FileBox getLib(long bno);
}