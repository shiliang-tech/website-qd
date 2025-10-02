package com.qd.controller.visitor;


import com.qd.result.Result;
import com.qd.service.CategoryService;
import com.qd.vo.CategoryOverviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("visitorCategoryController")
@RequestMapping("/categories")
@Api(tags ="用户查看分类接口" )
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation("查看分类概况")
    public Result<List<CategoryOverviewVO>> getCategoryOverview() {
        List<CategoryOverviewVO> list = categoryService.getOverview();
        return Result.success(list);
    }


}
