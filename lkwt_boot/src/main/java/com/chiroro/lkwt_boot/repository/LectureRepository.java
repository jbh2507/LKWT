package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.Lecture;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * LectureRepository
 */
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    
}