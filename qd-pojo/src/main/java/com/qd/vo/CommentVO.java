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
public class CommentVO {
    // 评论ID
    private Long id;
    // 所属文章ID
    private Long articleId;
    // 评论用户ID（匿名为空）
    private Long userId;
    // 访客昵称或用户昵称快照
    private String nickname;
    // 评论内容
    private String content;
    // 父评论ID（二级回复用，根评论为null）
    private Long parentId;
    // 根评论ID（便于整树查询）
    private Long rootId;
    // 被回复用户ID
    private Long replyToUserId;
    // 冗余：被回复用户昵称
    private String replyToNickname;
    // 楼层层级：1=根，2=子
    private Integer level;
    // 评论状态：0=待审,1=通过,2=屏蔽
    private Integer status;
    // 备注
    private String remark;
    // 创建时间
    private LocalDateTime createdAt;
    // 更新时间
    private LocalDateTime updatedAt;
    // 子评论列表（仅二级）
    private List<CommentVO> children;
}
