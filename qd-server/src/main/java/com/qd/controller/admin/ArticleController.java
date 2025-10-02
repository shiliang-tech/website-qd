package com.qd.controller.admin;

import com.qd.dto.ArticleDTO;
import com.qd.entity.Article;
import com.qd.result.Result;
import com.qd.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminArticleController")
@RequestMapping("/admin/articles")
@Slf4j
@Api(tags = "高级用户发帖增删改相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping
    @ApiOperation("创建文章")
    public Result insertArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.insert(articleDTO);
        return Result.success();
    }


    @PutMapping
    @ApiOperation("修改文章")
    public Result updateArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.update(articleDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除文章")
    public Result deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return Result.success();
    }
}
