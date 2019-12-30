package com.chiroro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiroro.mapper.UsersMapper;

import lombok.Setter;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

	@Setter(onMethod_ = @Autowired)
	private UsersMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return mapper.selectDetail(username);
	}

}
