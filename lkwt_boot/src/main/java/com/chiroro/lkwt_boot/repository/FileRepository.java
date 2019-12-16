package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.File;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FileRepository
 */
public interface FileRepository extends JpaRepository<File, String> {
    
}