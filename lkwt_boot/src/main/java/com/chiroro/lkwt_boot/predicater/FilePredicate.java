package com.chiroro.lkwt_boot.predicater;

import com.chiroro.lkwt_boot.domain.QFile;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * FilePredicate
 */
public class FilePredicate {
    public static Predicate search(SearchDTO dto){
        QFile qFile = QFile.file;

        String category = dto.getCategory();
        String keyword = dto.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qFile.fileBox.bno.eq(dto.getNo()));
        
        if(category != null){
            if(category.contains("N")) builder.and(qFile.fname.like("%"+keyword+"%"));
        }

        return builder;
    }
    
}