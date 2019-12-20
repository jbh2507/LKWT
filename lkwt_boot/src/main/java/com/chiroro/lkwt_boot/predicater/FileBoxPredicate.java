package com.chiroro.lkwt_boot.predicater;


import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.QFileBox;
import com.chiroro.lkwt_boot.dto.SearchDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * FileBoxPredicate
 */
public class FileBoxPredicate {
    public static Predicate search(SearchDTO dto){

        QFileBox qfilebox = QFileBox.fileBox;

        String category = dto.getCategory();
        String keyword = dto.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qfilebox.cno.eq(dto.getNo()));
        builder.and(qfilebox.tag.eq(dto.getTag()));

        // 검색
        if(category != null){
            if(category.contains("T")) builder.and(qfilebox.title.like("%"+keyword+"%"));
            if(category.contains("C")) builder.and(qfilebox.content.like("%"+keyword+"%"));
            if(category.contains("W")) ;
        }
       
        return builder;
    }
}