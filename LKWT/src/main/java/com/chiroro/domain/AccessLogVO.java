package com.chiroro.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccessLogVO {
	
	private Long fno, lno;
	private String userName;
	private LocalDateTime date;
	
}
