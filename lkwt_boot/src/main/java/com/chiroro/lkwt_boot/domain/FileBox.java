package com.chiroro.lkwt_boot.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import groovy.transform.ToString;
import lombok.Data;

/**
 * FileBoxVO
 */
@Entity
@Table(name = "filebox")
@Data
@ToString(excludes = "files")
public class FileBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private Long cno;

    private Character tag;

    private String title;

    private String content;

    @CreationTimestamp
    private Date regDate;

    @OneToMany(mappedBy = "fileBox"
        ,cascade = {CascadeType.PERSIST, CascadeType.MERGE}
        ,orphanRemoval = true
        ,fetch = FetchType.LAZY)
    private List<File> files;

    
}