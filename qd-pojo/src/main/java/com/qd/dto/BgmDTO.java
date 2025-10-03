package com.qd.dto;

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
public class BgmDTO implements Serializable {

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

    private Integer status;
}
