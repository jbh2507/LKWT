package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AccessLogVO {
	
	private String fname, userName;
	private Date date;
	
}
