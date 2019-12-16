package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.FileBox;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FileBoxRepository
 */
public interface FileBoxRepository extends JpaRepository<FileBox, Long> {

    //리스트
    //  전체
    //  날짜
    //  제목
    //조회
}