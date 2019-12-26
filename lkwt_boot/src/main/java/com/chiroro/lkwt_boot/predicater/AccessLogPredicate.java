package com.chiroro.lkwt_boot.predicater;


import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.QAccessLog;
import com.chiroro.lkwt_boot.domain.QFileBox;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * FileBoxPredicate
 */
public class AccessLogPredicate {
    public static Predicate search(SearchDTO dto){

        QAccessLog qLog = QAccessLog.accessLog;

        String category = dto.getCategory();
        String keyword = dto.getKeyword();
        
        BooleanBuilder builder = new BooleanBuilder();
        
        if(category != null){
            if(category.contains("F")) builder.and(qLog.file.fileBox.bno.eq(dto.getNo()));
            if(category.contains("U")) builder.and(qLog.userName.eq(keyword));
        }

        return builder;
    }
}