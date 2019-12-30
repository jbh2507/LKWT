package com.chiroro.lkwt_boot.service;

import javax.transaction.Transactional;

import com.chiroro.lkwt_boot.domain.User;
import com.chiroro.lkwt_boot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.Setter;

/**
 * UserServiceImpl
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = repo.findById(username).get();
        return result;
    }
}