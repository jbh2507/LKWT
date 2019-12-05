package com.chiroro.dto;

import lombok.Data;

@Data
public class PagingSource {
	private Integer page, amount;
	private Long no;
	private String category, keyword;
	
	public PagingSource() {
		this.page = 1;
		this.amount = 10;
	}
	
	public String[] getCategorys() {
		return category.split("");
	}
	
	public Integer getLimit() {
		return (page-1)*amount;
	}
}
