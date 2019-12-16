package com.chiroro.lkwt_boot.domain;

import java.sql.Date;

import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AccessLog
 */
@Embeddable
@Getter
@NoArgsConstructor
public class AccessLog {
    private String userName;

    public AccessLog(String userName){
        this.userName = userName;
    }

    @CreationTimestamp
    private Date date;
}