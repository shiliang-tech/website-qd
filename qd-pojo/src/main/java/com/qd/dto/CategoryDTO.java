package com.qd.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    //分类名称
    private String name;
    // 排序值
    private Integer sort;
    // 状态：1=启用,0=禁用
    private Integer status;

}
