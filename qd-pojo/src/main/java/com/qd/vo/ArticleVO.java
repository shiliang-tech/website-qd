package com.qd.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO {
    // 文章ID
    private Long id;
    // 标题
    private String title;
    // 短链接/别名（slug）
    private String slug;
    // 摘要
    private String summary;
    // 封面图地址
    private String coverUrl;
    // 主分类名称（冗余字段）
    private String mainCategory;
    // 标签名称列表（冗余字段）
    private List<String> tagNames;
    // 浏览量
    private Long viewCount;
    // 点赞量
    private Long likeCount;
    // 发布时间
    private LocalDateTime publishedAt;
}
