package com.chiroro.dto;

import lombok.Data;

@Data
public class PagingSource {
	private Integer page, amount;
	private Long no;
	private String category, keyword;
	private String tag;
	
	public PagingSource() {
		this.page = 1;
		this.amount = 10;
	}
	
	public String[] getCategorys() {
		if(category == null) return null;
		return category.split("");
	}
	
	public Integer getLimit() {
		return (page-1)*amount;
	}
}
