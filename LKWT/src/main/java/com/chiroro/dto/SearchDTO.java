package com.chiroro.dto;

import lombok.Data;

@Data
public class SearchDTO {
	
	private Long no;
	private String category, keyword;
	private String tag;
	
	public SearchDTO() {
		tag = "이해도";
	}
}
