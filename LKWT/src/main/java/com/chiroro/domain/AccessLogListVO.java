package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AccessLogListVO {
	private String fname, userName;
	private Date date;
}
