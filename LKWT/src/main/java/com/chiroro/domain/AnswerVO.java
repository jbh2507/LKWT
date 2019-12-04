package com.chiroro.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AnswerVO {
	private Long qno, ano;
	private String username;
	private Integer indicator;
	private Date regDate;
}
