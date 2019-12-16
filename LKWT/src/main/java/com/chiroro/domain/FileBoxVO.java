package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class FileBoxVO {
	
	private Long bno, cno;
	private Character tag;
	private String title, content;
	private Date regDate;
}
