package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.context.BaseContext;
import com.qd.dto.ArticleDTO;
import com.qd.dto.ArticlePageQueryDTO;
import com.qd.entity.Article;
import com.qd.entity.User;
import com.qd.mapper.ArticleMapper;
import com.qd.mapper.UserMapper;
import com.qd.result.PageResult;
import com.qd.service.ArticleService;
import com.qd.service.UserService;
import com.qd.utils.SlugUtil;
import com.qd.vo.ArticleDetailVO;
import com.qd.vo.ArticleVO;
import com.qd.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;


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

    /**
     * 新增文章
     * @param articleDTO
     */
    public void insert(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        article.setAuthorId(BaseContext.getCurrentId());
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        article.setPublishedAt(LocalDateTime.now());
        article.setStatus(1);

        //md文件转html
        Parser parser = Parser.builder().build();
        Node document = parser.parse(articleDTO.getContentMd());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String contentHtml = renderer.render(document);
        article.setContentHtml(contentHtml);

        //标题转slug
        article.setSlug(SlugUtil.slugifyWithPinyin(article.getTitle()));

        article.setIsTop(0);
        article.setIsRecommend(0);
        article.setCommentCount(0);
        article.setLikeCount((long) 0);
        article.setViewCount((long) 0);

        //设置作者名字
        User user = userMapper.getById(article.getAuthorId());
        article.setAuthorName(user.getNickname());

        articleMapper.insert(article);
    }
}
