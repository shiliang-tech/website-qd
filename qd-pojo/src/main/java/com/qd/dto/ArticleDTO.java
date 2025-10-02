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
public class ArticleDTO implements Serializable {
    //文章id
    private Long id;
    // 标题
    private String title;
    // 摘要
    private String summary;
    // 封面图URL
    private String coverUrl;
    // Markdown格式的正文
    private String contentMd;
}