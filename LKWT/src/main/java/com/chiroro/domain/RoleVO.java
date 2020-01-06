package com.chiroro.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class RoleVO implements GrantedAuthority{
	private String userName;
	
	private String authority;
	
	@Override
	public String getAuthority() {
		return authority;
	}

}
