package com.chiroro.lkwt_boot.dto;

import java.util.List;

import com.chiroro.lkwt_boot.domain.File;
import com.chiroro.lkwt_boot.domain.FileBox;

import lombok.Data;

/**
 * ResourceDTO
 */
@Data
public class ResourceDTO {
    private FileBox fileBox;
    private List<File> files;
}