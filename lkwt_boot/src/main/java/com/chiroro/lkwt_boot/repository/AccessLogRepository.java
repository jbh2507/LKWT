package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.AccessLog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AccessLogRepository
 */
public interface AccessLogRepository extends JpaRepository<AccessLog, Long>{

    
}