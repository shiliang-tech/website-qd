package com.qd.controller.visitor;

import com.qd.dto.ArticlePageQueryDTO;
import com.qd.dto.DishPageQueryDTO;
import com.qd.result.PageResult;
import com.qd.result.Result;
import com.qd.service.ArticleService;
import com.qd.vo.ArticleDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@Slf4j
@Api(tags = "访客浏览相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping()
    @ApiOperation("帖子分页查询")
    public Result<PageResult> page(ArticlePageQueryDTO articlePageQueryDTO) {
        PageResult pageResult = articleService.pageQuery(articlePageQueryDTO);
        return Result.success(pageResult);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询帖子详情")
    public Result<ArticleDetailVO> getById(@PathVariable Integer id) {
        ArticleDetailVO vo = articleService.getById(id);
        return Result.success(vo);
    }

}
