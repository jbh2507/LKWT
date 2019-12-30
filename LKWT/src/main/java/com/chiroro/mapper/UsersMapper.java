package com.chiroro.mapper;

import com.chiroro.domain.UserDetailVO;
import com.chiroro.domain.UserVO;

public interface UsersMapper {
	
	public void insert(UserVO vo);
	
	public UserDetailVO selectDetail(String username);
}
