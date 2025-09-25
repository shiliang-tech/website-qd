package com.qd.service.impl;

import com.qd.dto.ArticlePageQueryDTO;
import com.qd.result.PageResult;
import com.qd.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    /**
     * 评论分页查询
     * @param articlePageQueryDTO
     * @return
     */
    public PageResult pageQuery(ArticlePageQueryDTO articlePageQueryDTO) {
        return null;
    }
}
