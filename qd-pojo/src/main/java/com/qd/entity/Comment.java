package com.qd.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    // 评论ID
    private Long id;
    // 文章ID（逻辑外键）
    private Long articleId;
    // 冗余：文章标题快照
    private String articleTitle;
    // 用户ID（逻辑外键，匿名为空）
    private Long userId;
    // 访客昵称或用户昵称快照
    private String nickname;
    // 邮箱
    private String email;
    // 评论内容
    private String content;
    // 父评论ID（二级回复用）
    private Long parentId;
    // 根评论ID（便于整树查询）
    private Long rootId;
    // 被回复用户ID
    private Long replyToUserId;
    // 冗余：被回复用户昵称
    private String replyToNickname;
    // 楼层层级，1=根，2=子
    private Integer level;
    // 评论状态，1=通过,2=屏蔽
    private Integer status;
    // 评论者IP地址
    private String ip;
    // User-Agent（浏览器/客户端信息）
    private String ua;
    // 备注
    private String remark;
    // 创建时间
    private LocalDateTime createdAt;
    // 更新时间
    private LocalDateTime updatedAt;
    // 删除时间（逻辑删除）
    private LocalDateTime deletedAt;
}
