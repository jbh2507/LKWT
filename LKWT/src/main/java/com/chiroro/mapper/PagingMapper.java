package com.chiroro.mapper;

import java.util.List;

import com.chiroro.dto.PagingSource;

public interface PagingMapper<E> {

	public long selectTotal(PagingSource source);
	public List<E> selectList(PagingSource source);
	
}
