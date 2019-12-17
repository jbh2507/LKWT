package com.chiroro.domain;

import java.util.List;

import lombok.Data;

@Data
public class FileBoxViewVO {
	private FileBoxVO filebox;
	private List<FileVO> files;
}
