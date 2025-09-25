package com.qd.dto;

import lombok.Data;

@Data
public class CommentPageQueryDTO {
    private int page;

    private int size;

    private String order;
}
