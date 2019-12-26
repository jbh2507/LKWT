package com.chiroro.lkwt_boot.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.chiroro.lkwt_boot.domain.AccessLog;
import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.dto.FileDTO;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.predicater.AccessLogPredicate;
import com.chiroro.lkwt_boot.predicater.FileBoxPredicate;
import com.chiroro.lkwt_boot.repository.AccessLogRepository;
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

    @Setter(onMethod_ = @Autowired)
    private AccessLogRepository logRepo;

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
    public boolean addSubmission(FileDTO file) {
        String userName = "tester";

        SearchDTO search = new SearchDTO();
        search.setCategory("UF");
        search.setKeyword(userName);
        search.setNo(file.getBno());

        File fileVO = new File();
        fileVO.setFname(file.getFname());
        fileVO.setRegDate(file.getRegDate());
        fileVO.setFileBox(boxRepo.getOne(file.getBno()));

        long fno = logRepo.findOne(AccessLogPredicate.search(search)).get().getFile().getFno();
        try{
            fileRepo.deleteById(fno);
            
            AccessLog newLog = new AccessLog();
            newLog.setFile(fileRepo.save(fileVO));
            newLog.setUserName(userName);

            logRepo.save(newLog);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean updateSubmission(FileDTO file) {
        String userName = "tester";

        SearchDTO search = new SearchDTO();
        search.setCategory("UF");
        search.setKeyword(userName);
        search.setNo(file.getBno());

        File fileVO = new File();
        fileVO.setFname(file.getFname());
        fileVO.setRegDate(file.getRegDate());
        fileVO.setFileBox(boxRepo.getOne(file.getBno()));

        Optional<AccessLog> casedLog = logRepo.findOne(AccessLogPredicate.search(search));
        if(casedLog.isPresent()){
            long fno = casedLog.get().getFile().getFno();
            fileRepo.deleteById(fno);

        }
        try{
            AccessLog newLog = new AccessLog();
            newLog.setFile(fileRepo.save(fileVO));
            newLog.setUserName(userName);

            logRepo.save(newLog);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<File> getFileList(long bno) {

        FileBox box = boxRepo.getOne(bno);

        List<File> result = fileRepo.findByFileBox(box);

        return result;
    }

    @Override
    public boolean isSubmited(long bno) {
        
        SearchDTO dto = new SearchDTO();

        dto.setNo(bno);
        dto.setCategory("UF");
        dto.setKeyword("tester");

        boolean result = logRepo.exists(AccessLogPredicate.search(dto));
        
        return result;
    }

    @Override
    public void addAccesslog(long fno) {

        AccessLog access = new AccessLog();
        access.setFile(fileRepo.getOne(fno));
        access.setUserName("tester");

        logRepo.save(access);
        
    }

    
}