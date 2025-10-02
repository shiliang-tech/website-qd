package com.qd.entity;

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
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 标签名
    private String name;

    // 唯一标识 slug
    private String slug;

    // 冗余：文章数量快照
    private Integer articleCount;

    // 状态: 1=启用,0=禁用
    private Integer status;

    // 备注
    private String remark;

    // 创建时间
    private LocalDateTime createdAt;

    // 更新时间
    private LocalDateTime updatedAt;

    // 删除时间
    private LocalDateTime deletedAt;
}
