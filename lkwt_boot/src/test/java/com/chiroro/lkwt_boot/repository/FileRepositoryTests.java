package com.chiroro.lkwt_boot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.chiroro.lkwt_boot.domain.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import groovy.util.logging.Slf4j;

/**
 * FileRepositoryTests
 */
@SpringBootTest
@Slf4j
public class FileRepositoryTests {

    @Autowired
    private FileRepository repo;


    
    @Test
    public void isExist(){
        assertNotNull(repo);
    }

    @Test
    public void createTest(){
        File file = new File();
      

        
        repo.save(new File());
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