package com.qd.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 标签名
    private String name;

    // 状态: 1=启用,0=禁用
    private Integer status;
}
