package com.qd.controller.admin;


import com.qd.dto.TagDTO;
import com.qd.result.Result;
import com.qd.service.TagService;
import com.qd.vo.TagOverviewVO;
import com.qd.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminTagController")
@RequestMapping("/admin/tags")
@Api(tags = "管理员标签相关接口")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 管理员查看标签总览
     * @return
     */
    @GetMapping
    @ApiOperation("管理员查看标签总览")
    public Result<List<TagVO>> getTagOverview() {
        List<TagVO> list = tagService.list();
        return Result.success(list);
    }

    /**
     * 新增标签
     * @return
     */
    @PostMapping
    @ApiOperation("新增标签")
    public Result createTag(@RequestBody TagDTO tagDTO) {
        tagService.createTag(tagDTO);
        return Result.success();
    }

    /**
     * 修改标签
     * @param tagDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改标签")
    public Result updateTag(@RequestBody TagDTO tagDTO) {
        tagService.updateTag(tagDTO);
        return Result.success();
    }

    /**
     * 删除标签
     * @param tagDTO
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除标签")
    public Result deleteTag(@RequestBody TagDTO tagDTO) {
        tagService.deleteTag(tagDTO);
        return Result.success();
    }

}
