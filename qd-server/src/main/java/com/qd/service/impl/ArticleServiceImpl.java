package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.dto.ArticlePageQueryDTO;
import com.qd.mapper.ArticleMapper;
import com.qd.result.PageResult;
import com.qd.service.ArticleService;
import com.qd.vo.ArticleDetailVO;
import com.qd.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 帖子分页查询
     * @param articlePageQueryDTO
     * @return
     */
    public PageResult pageQuery(ArticlePageQueryDTO articlePageQueryDTO) {
        PageHelper.startPage(articlePageQueryDTO.getPage(), articlePageQueryDTO.getSize());
        Page<ArticleVO> page = articleMapper.pageQuery(articlePageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查看帖子的详细信息
     * @param id
     * @return
     */
    public ArticleDetailVO getById(Integer id) {
        ArticleDetailVO vo = articleMapper.getById(id.longValue());
        return vo;
    }
}
