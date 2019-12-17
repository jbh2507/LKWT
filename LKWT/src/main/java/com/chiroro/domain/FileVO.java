package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class FileVO {
	
	private Long fno;
	private String fname;
	private Long bno;
	private Date regDate;
}
