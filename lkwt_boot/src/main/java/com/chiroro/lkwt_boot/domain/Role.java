package com.chiroro.lkwt_boot.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

/**
 * Role
 */
@Data
@Embeddable
public class Role implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    
}