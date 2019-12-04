package com.chiroro.dto;

import java.util.List;

import com.chiroro.mapper.PagingMapper;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO<E>{
	private PagingSource source;
	private Integer start, end, lastPage, pageAmount;
	private Long total;
	private Boolean next, prev;
	private List<E> list;
	
	public PageDTO(PagingSource source, List<E> list, Long total) {
		this.source = source;
		this.list = list;
		this.total = total;
		this.pageAmount = 10;
		
		int page = source.getPage();
		int amount = source.getAmount();
		
		lastPage = (int) Math.ceil((double) total / amount);
		start = page - ((page - 1) % amount);
		int tmpEnd = start + pageAmount - 1;
		end = tmpEnd > lastPage ? lastPage : tmpEnd;
		next = tmpEnd < lastPage;
		prev = start > 1;
	}
	
	public PageDTO(PagingSource source, PagingMapper<E> mapper) {
		this(source, mapper.selectList(source), mapper.selectTotal(source));
	}
}
