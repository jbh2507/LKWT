package com.chiroro.lkwt_boot.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString(excludes = "filebox")
public class File {
    
    @Id
    private String fname;

    @ManyToOne
    private FileBox fileBox;

    @CreationTimestamp
    private Date regDate;

    @ElementCollection
    @CollectionTable(
        name = "accesslog",
        joinColumns = {@JoinColumn(name="fname")}
        )
    private List<AccessLog> accesslog;
}