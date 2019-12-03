package com.chiroro.mapper;

import com.chiroro.dto.PageDTO;
import com.chiroro.dto.PagingSource;

public abstract class AbstractPagingMapper<E> implements PagingMapper<E>{

	public PageDTO getPageDTO(PagingSource source) {
		return new PageDTO<E>(source, selectList(source), selectTotal(source));
	}
}
