package com.qd.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticlePageQueryDTO  implements Serializable {

    private int page;

    private int size;

    private String keyword;

    //分类id
    private Integer categoryId;

    //标签id
    private Integer tagId;

    //排序方式
    private String  sort;

}
