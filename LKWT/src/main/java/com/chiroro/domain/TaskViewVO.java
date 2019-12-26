package com.chiroro.domain;

import java.util.List;

import lombok.Data;

@Data
public class TaskViewVO {
	private FileBoxVO filebox;
	private List<TaskFileVO> files;
}
