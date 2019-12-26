package com.chiroro.lkwt_boot.dto;

import java.sql.Date;

import lombok.Data;

/**
 * FileDTO
 */
@Data
public class FileDTO {
    private Long bno;
    private String fname;
    private Date regDate;
    
}