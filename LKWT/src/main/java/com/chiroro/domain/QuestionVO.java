package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class QuestionVO {
	private Long qno, cno;
	private String category, content;
	private Date regDate;
}
