package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.FileBox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * FileBoxRepository
 */
public interface FileBoxRepository extends JpaRepository<FileBox, Long>, QuerydslPredicateExecutor<FileBox> {


}