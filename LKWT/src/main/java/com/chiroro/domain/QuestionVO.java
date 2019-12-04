package com.chiroro.domain;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionVO {
	private Long qno, cno;
	private String category, content;
	private Date regDate;
}
