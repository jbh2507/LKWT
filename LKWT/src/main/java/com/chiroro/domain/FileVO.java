package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class FileVO {
	
	private Long fno;
	private String fname;
	private String orginName;
	private Long bno;
	private Date regDate;
	
//	public void setFname(String fname) {
//		this.fname = fname;
//		this.orginName = fname.substring(fname.indexOf('_'));
//	}
}
