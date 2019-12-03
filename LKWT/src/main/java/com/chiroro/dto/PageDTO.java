package com.chiroro.dto;

import java.util.List;

public class PageDTO<E> {
	private Integer page;
	private List<E> list;
	
	public PageDTO(PagingSource source, List<E> list, Long total) {
		
	}
}
