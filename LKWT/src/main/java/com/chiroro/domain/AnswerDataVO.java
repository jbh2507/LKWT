package com.chiroro.domain;

import lombok.Data;

@Data
public class AnswerDataVO {
	private String cname, content, tag;
	private Long qno;
	private Boolean comment;
	private Double resAvg, resRate;
	
	public AnswerDataVO() {
		resAvg = 0.0;
	}
	
	public double getY() {
		return resAvg/100.0;
	} 
}
