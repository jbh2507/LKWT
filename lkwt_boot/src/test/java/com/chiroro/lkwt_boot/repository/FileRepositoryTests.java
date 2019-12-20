package com.chiroro.lkwt_boot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.dto.SearchDTO;

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
    public void findAllTest(){
        SearchDTO dto = new SearchDTO();
        dto.setTag('T');
        dto.setNo(1L);


    }
}