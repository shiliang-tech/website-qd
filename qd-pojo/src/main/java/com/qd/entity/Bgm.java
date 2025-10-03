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
public class Bgm implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 歌曲名称
    private String title;

    // 歌手
    private String artist;

    // 歌曲URL（音频存储地址，如 OSS 链接）
    private String audioUrl;

    // 排序值，越小越靠前
    private Integer sort;

    // 备注（比如版权、来源说明）
    private String remark;

    // 创建时间
    private LocalDateTime createdAt;

    // 更新时间
    private LocalDateTime updatedAt;

    private Integer status;
}
