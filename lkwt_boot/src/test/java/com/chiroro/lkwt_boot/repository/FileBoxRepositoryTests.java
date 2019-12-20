package com.chiroro.lkwt_boot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import com.chiroro.lkwt_boot.domain.FileBox;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.predicater.FileBoxPredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import lombok.extern.slf4j.Slf4j;



/**
 * FileBoxRepository
 */
@SpringBootTest
@Slf4j
public class FileBoxRepositoryTests {

    @Autowired
    private FileBoxRepository repo;
    
    private FileBox fb;
    
    @BeforeEach
    public void ready(){
         
    }

    @Test
    public void isExist(){
        assertNotNull(repo);
    }

   
    @Test
    @Transactional
    public void readListTest(){
        String title = null;
        String content = null;
        Integer weekOfDay = null;

        int page = 0;
        int size = 10;
        


        Pageable pageable = PageRequest.of(page, size, Direction.DESC, "bno");
        

        SearchDTO dto = new SearchDTO();
        dto.setNo(1L);
        dto.setTag('T');

        Page<FileBox> boxPage = repo.findAll(FileBoxPredicate.search(dto), pageable);
        boxPage.forEach(e -> {
            log.info(e.toString());
        });

    
    }
    
}