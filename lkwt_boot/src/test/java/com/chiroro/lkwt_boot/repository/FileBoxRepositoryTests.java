package com.chiroro.lkwt_boot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.chiroro.lkwt_boot.domain.FileBox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import groovy.util.logging.Slf4j;

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
    public void createTest(){
        for(int i=0; i<91; i++){
            fb = new FileBox();
            fb.setCno(1L);
            fb.setContent("test"+System.currentTimeMillis());
            fb.setTitle("test box "+i);
            fb.setTag('L');

            repo.save(fb);
        }
    }

    @Test
    public void readTest(){
        
    }

    @Test
    public void readListTest(){
        
    }

    @Test
    public void updateTest(){
        
    }

    @Test
    public void deleteTest(){
        
    }



    
}