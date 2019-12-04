package com.chiroro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionListVO {
	private QuestionVO question;
	private Integer responseRate, avgAnswer;
}
