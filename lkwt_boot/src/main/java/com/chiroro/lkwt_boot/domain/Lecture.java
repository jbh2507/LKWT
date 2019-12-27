package com.chiroro.lkwt_boot.domain;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * Class
 */
@Data
@Entity
@Table(name = "Lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private Long sno;

    private String cname;

    @Column(name = "startdate")
    private Date starDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "regdate")
    @CreationTimestamp
    private LocalDateTime regDate;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> users;
    
}