package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.chiroro.lkwt_boot.predicater.AccessLogPredicate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * AccessLogRepositoryTests
 */
@SpringBootTest
@Slf4j
public class AccessLogRepositoryTests {

    @Autowired
    AccessLogRepository repo;

    @Test
    public void isExistFileTest(){
        SearchDTO dto = new SearchDTO();
        dto.setNo(22L);
        dto.setCategory("UF");
        dto.setKeyword("tester");

        boolean result = repo.exists(AccessLogPredicate.search(dto));
        log.info(result+"");
    }
}