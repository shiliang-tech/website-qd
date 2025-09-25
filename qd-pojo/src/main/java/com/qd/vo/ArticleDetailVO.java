package com.qd.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVO {
    // 主键ID
    private Long id;
    // 标题
    private String title;
    // 唯一标识slug
    private String slug;
    // 摘要
    private String summary;
    // 封面图URL
    private String coverUrl;
    // Markdown格式的正文
    private String contentMd;
    // HTML格式的正文
    private String contentHtml;
    // 状态: 0=草稿,1=已发布,2=定时发布,3=下线
    private Integer status;
    // 是否置顶 (0=否,1=是)
    private Integer isTop;
    // 是否推荐 (0=否,1=是)
    private Integer isRecommend;
    // SEO标题
    private String seoTitle;
    // SEO关键词
    private String seoKeywords;
    // SEO描述
    private String seoDescription;
    // 作者ID（逻辑外键）
    private Long authorId;
    // 作者名快照（冗余）
    private String authorName;
    // 主分类ID（逻辑外键）
    private Long mainCategoryId;
    // 主分类名快照（冗余）
    private String mainCategoryName;
    // 标签ID列表，逗号分隔，如: 3,5,7
    private String tagIdsCsv;
    // 标签名称列表，逗号分隔
    private String tagNamesCsv;
    // 浏览量
    private Long viewCount;
    // 点赞量
    private Long likeCount;
    // 评论数
    private Integer commentCount;
    // 发布时间
    private LocalDateTime publishedAt;
    // 扩展字段（JSON）
    private String extra;
    // 备注
    private String remark;
    // 创建时间
    private LocalDateTime createdAt;
    // 更新时间
    private LocalDateTime updatedAt;
    // 删除时间
    private LocalDateTime deletedAt;
}
