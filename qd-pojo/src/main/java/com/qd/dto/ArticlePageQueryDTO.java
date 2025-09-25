package com.qd.dto;

import lombok.Data;

@Data
public class ArticlePageQueryDTO {

    private int page;

    private int size;

    private String keyword;

    //分类id
    private Integer categoryId;

    //标签id
    private Integer tagId;

}
