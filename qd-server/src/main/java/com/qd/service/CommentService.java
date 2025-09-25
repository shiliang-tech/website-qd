package com.qd.service;

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
}
