package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.AccessLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * AccessLogRepository
 */
public interface AccessLogRepository extends JpaRepository<AccessLog, Long>, QuerydslPredicateExecutor<AccessLog>{

    
}