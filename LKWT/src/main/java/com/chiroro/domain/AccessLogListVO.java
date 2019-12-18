package com.chiroro.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccessLogListVO {
	private String fname, userName;
	private LocalDateTime date;
}
