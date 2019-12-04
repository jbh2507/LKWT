package com.chiroro.dto;

import lombok.Data;

@Data
public class PagingSource {
	private Integer page, amount;
	private String category, keyword;
	
	public String[] getCategorys() {
		return category.split("");
	}
	
	public Integer getLimit() {
		return (page-1)*amount;
	}
}
