package com.qd.service;

import com.qd.dto.ArticleDTO;
import com.qd.dto.ArticlePageQueryDTO;
import com.qd.result.PageResult;
import com.qd.vo.ArticleDetailVO;

public interface ArticleService {

    /**
     * 文章的分页查询
     * @param articlePageQueryDTO
     * @return
     */
    PageResult pageQuery(ArticlePageQueryDTO articlePageQueryDTO);

    /**
     * 查询帖子的详细信息
     * @param id
     * @return
     */
    ArticleDetailVO getById(Integer id);

    /**
     * 新增文章
     * @param articleDTO
     */
    void insert(ArticleDTO articleDTO);
}
