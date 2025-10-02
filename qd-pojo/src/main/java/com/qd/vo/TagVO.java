package com.qd.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 标签名
    private String name;

    // 状态: 1=启用,0=禁用
    private Integer status;

}
