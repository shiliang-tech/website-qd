package com.qd.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryOverviewVO {
    // 主键ID
    private Long id;

    // 分类名称
    private String name;

    // 排序值
    private Integer sort;
}
