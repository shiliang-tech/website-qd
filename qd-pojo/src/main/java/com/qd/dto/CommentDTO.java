package com.qd.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentDTO implements Serializable {
    // 文章ID（逻辑外键）
    private Long articleId;
    // 评论内容
    private String content;
    // 父评论ID（二级回复用）
    private Long parentId;
    // 根评论ID（二级回复用）
    private Long rootId;
    // 被回复用户ID
    private Long replyToUserId;
}
