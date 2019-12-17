package com.chiroro.domain;

import lombok.Data;

@Data
public class FileListVO {
	private FileVO file;
	private Integer accessCount;
}
