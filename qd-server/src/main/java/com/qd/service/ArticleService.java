package com.qd.service;

import com.qd.dto.ArticlePageQueryDTO;
import com.qd.result.PageResult;

public interface ArticleService {

    /**
     * 文章的分页查询
     * @param articlePageQueryDTO
     * @return
     */
    PageResult pageQuery(ArticlePageQueryDTO articlePageQueryDTO);
}
