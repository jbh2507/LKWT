package com.chiroro.lkwt_boot.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import groovy.transform.ToString;
import lombok.Data;

/**
 * FileVO
 */
@Entity
@Table(name = "file")
@Data
@ToString(excludes = {"filebox", "file"})
public class File {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String fname;
    
    @CreationTimestamp
    private Date regDate;
    
        @ManyToOne
        private FileBox fileBox;

    @OneToMany(mappedBy = "file"
        ,cascade = {CascadeType.PERSIST, CascadeType.MERGE}
        ,orphanRemoval = true
        ,fetch = FetchType.LAZY)
    private List<AccessLog> accesslog;
}