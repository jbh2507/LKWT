package com.chiroro.lkwt_boot.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * User
 */
@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {

    @Id
    private String username;

    private String password;
    
    private Boolean enabled;
    
    private Boolean accountNonExpired;
    
    private Boolean accountNonLocked;
    
    private Boolean credentialsNonExpired;
    
    @CreationTimestamp
    @Column(name = "regdate")
    private LocalDateTime regDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Lecture> lectures; 

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = {@JoinColumn(name = "username")}, name = "authorities")
    private Set<Role> authorities;

    public User(){
        this.enabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
    }

    @Override
	public Set<Role> getAuthorities() {
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

    public void setLecture(Lecture lecture){
        if(lectures == null) lectures = new HashSet<>();
        
        this.lectures.add(lecture);
    }
} 