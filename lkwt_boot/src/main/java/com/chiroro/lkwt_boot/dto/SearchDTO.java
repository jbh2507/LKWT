package com.chiroro.lkwt_boot.dto;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString
public class SearchDTO {
    private String category, keyword;
    private Character tag;
    private Long no;
} 