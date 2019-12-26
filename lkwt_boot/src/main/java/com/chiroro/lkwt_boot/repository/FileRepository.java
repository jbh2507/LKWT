package com.chiroro.lkwt_boot.repository;

import java.util.List;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * FileRepository
 */
public interface FileRepository extends JpaRepository<File, Long>, QuerydslPredicateExecutor<File>{
    
    public List<File> findByFileBox(FileBox fileBox);
}