package com.chiroro.lkwt_boot.repository;

import java.util.List;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FileRepository
 */
public interface FileRepository extends JpaRepository<File, Long> {
    
    public List<File> findByFileBox(FileBox fileBox);
}