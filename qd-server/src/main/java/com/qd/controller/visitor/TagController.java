package com.qd.controller.visitor;

import com.qd.result.Result;
import com.qd.service.TagService;
import com.qd.vo.TagOverviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(tags = "用户查看标签相关接口")
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    @ApiOperation("查看标签总览")
    public Result<List<TagOverviewVO>> getTagOverview() {
        List<TagOverviewVO> list = tagService.getOverview();
        return Result.success(list);
    }

}
