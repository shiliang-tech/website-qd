package com.qd.controller.admin;

import com.qd.dto.ArticleDTO;
import com.qd.entity.Article;
import com.qd.result.Result;
import com.qd.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminArticleController")
@RequestMapping("/admin/articles")
@Slf4j
@Api(tags = "高级用户发帖增删改相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 发帖
     *
     * @param articleDTO
     * @return
     */
    @PostMapping
    @ApiOperation("创建文章")
    public Result insertArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.insert(articleDTO);
        return Result.success();
    }
}
