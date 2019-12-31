package com.chiroro.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AnswerDataVO {
	private Long qno;
	private Double resAvg;
	private Date minDate;
	private Integer countOfComment;
	
	public AnswerDataVO() {
		resAvg = 0.0;
	}
	
	public double getY() {
		return resAvg/100.0;
	} 
}
