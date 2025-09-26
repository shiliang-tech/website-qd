package com.qd.service;

import com.qd.dto.CommentDTO;
import com.qd.dto.CommentPageQueryDTO;
import com.qd.entity.Comment;
import com.qd.result.PageResult;

public interface CommentService {

    /**
     * 根据文章id获取评论
     * @param id
     * @param commentPageQueryDTO
     * @return
     */
    PageResult pageQuery(Integer id, CommentPageQueryDTO commentPageQueryDTO);

    /**
     * 在帖子下发表评论
     * @param commentDTO
     */
    void createComment(CommentDTO commentDTO);

    /**
     * 对评论进行回复
     * @param commentDTO
     */
    void reply(CommentDTO commentDTO);

    /**
     * 删除自己的评论
     * @param id
     */
    void delete(Integer id);
}
