package com.chiroro.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class UserDetailVO implements UserDetails{
	 	private String username;

	    private String password;
	    
	    private Boolean enabled;
	    
	    private Boolean accountNonExpired;
	    
	    private Boolean accountNonLocked;
	    
	    private Boolean credentialsNonExpired;
	    
	    private LocalDateTime regDate;

	    private List<ClassVO> lectures; 

	    private Set<RoleVO> authorities;

	    public UserDetailVO(){
	        this.enabled = true;
	        this.accountNonExpired = true;
	        this.accountNonLocked = true;
	        this.credentialsNonExpired = true;
	    }

	    @Override
		public Set<RoleVO> getAuthorities() {
			return authorities;
		}

	    @Override
		public String getPassword() {
			return password;
		}

	    @Override
		public String getUsername() {
			return username;
		}

	    @Override
		public boolean isEnabled() {
			return enabled;
		}

	    @Override
		public boolean isAccountNonExpired() {
			return accountNonExpired;
		}

	    @Override
		public boolean isAccountNonLocked() {
			return accountNonLocked;
		}

	    @Override
		public boolean isCredentialsNonExpired() {
			return credentialsNonExpired;
		}

	    public void setLecture(ClassVO lecture){
	        if(lectures == null) lectures = new ArrayList<>();
	        
	        this.lectures.add(lecture);
	    }
	    
	    public List<ClassVO> getLectures(){
	    	return this.lectures;
	    }
}
