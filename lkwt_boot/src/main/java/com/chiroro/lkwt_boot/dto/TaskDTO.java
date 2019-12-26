package com.chiroro.lkwt_boot.dto;

import com.chiroro.lkwt_boot.domain.FileBox;

import lombok.Data;

/**
 * TaskDTO
 */
@Data
public class TaskDTO {

    private FileBox fileBox;
    private boolean isSubmited;
}