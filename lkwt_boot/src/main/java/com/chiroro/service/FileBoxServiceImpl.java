package com.chiroro.service;


import java.sql.SQLException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.predicater.FileBoxPredicate;
import com.chiroro.lkwt_boot.repository.FileBoxRepository;
import com.chiroro.lkwt_boot.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Setter;

/**
 * FileBoxServiceImpl
 */
@Service
@Transactional
public class FileBoxServiceImpl implements FileBoxService {
    
    @Setter(onMethod_ = @Autowired)
    private FileBoxRepository boxRepo;
    
    @Setter(onMethod_ = @Autowired)
    private FileRepository fileRepo;

    @Override
    public FileBox getLib(long bno) {
        FileBox box = boxRepo.findById(bno).get();

        if(box.getTag() == 'L') return box;
        else throw new IllegalArgumentException(new SQLException("bno: "+bno+" is not ResourceBoard's bno"));
    }
    
    @Override
    public Page<FileBox> getLibList(Pageable page, SearchDTO dto) {
        dto.setTag('L');
        return  boxRepo.findAll(FileBoxPredicate.search(dto), page);
    }
    
    @Override
    public FileBox getTask(long bno) {
        FileBox box = boxRepo.findById(bno).get();

        if(box.getTag() == 'T') return box;
        else throw new IllegalArgumentException(new SQLException("bno: "+bno+" is not ResourceBoard's bno"));
    }
    
    @Override
    public Page<FileBox> getTaskList(Pageable page, SearchDTO dto) {
        dto.setTag('T');
        return boxRepo.findAll(FileBoxPredicate.search(dto), page);
    }
    
    @Override
    public boolean addSubmission(File file) {
        try{
            fileRepo.save(file);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean updateSubmission(File file) {
        try{
            fileRepo.deleteById(file.getFno());
            fileRepo.save(file);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}