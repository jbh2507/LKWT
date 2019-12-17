package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AccessLogVO {
	
	private Long fno, lno;
	private String userName;
	private Date date;
	
}
