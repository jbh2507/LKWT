package com.chiroro.lkwt_boot.repository;

import com.chiroro.lkwt_boot.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, String>{

    
}