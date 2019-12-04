package com.chiroro.mapper;

import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

@Deprecated
public abstract class AbstractPagingMapper<E> implements PagingMapper<E>{

	public PageDTO<E> getPageDTO(PagingSource source) {
		return new PageDTO<>(source, selectList(source), selectTotal(source));
	}
}
