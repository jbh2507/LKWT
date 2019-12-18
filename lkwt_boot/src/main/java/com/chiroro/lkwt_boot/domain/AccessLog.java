package com.chiroro.lkwt_boot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * AccessLog
 */
@Entity
@Table(name = "accesslog")
@Data
@ToString(exclude = "file")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    @Column(name = "username")
    private String userName;

    @ManyToOne
    private File file;

    @CreationTimestamp
    private LocalDateTime accessDate;
}