package com.chiroro.lkwt_boot.dto;

import lombok.Data;

/**
 * PageDTO
 */
@Data
public class PageSourceDTO {
    private String category, keyword;
    private Character tag;
    private Long no;

    private Integer page;
    private Integer amount;

    public PageSourceDTO(){
        this.page = 1;
        this.amount = 10;
    }

    public SearchDTO getSearchDTO(){
        SearchDTO search = new SearchDTO();
        search.setCategory(category);
        search.setKeyword(keyword);
        search.setNo(no);
        search.setTag(tag);

        return search;
    }
}