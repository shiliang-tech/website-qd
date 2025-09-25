package com.qd.controller.visitor;

import com.qd.dto.CommentPageQueryDTO;
import com.qd.entity.Comment;
import com.qd.mapper.CommentMapper;
import com.qd.result.PageResult;
import com.qd.result.Result;
import com.qd.service.CommentService;
import com.qd.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles/comments")
@Slf4j
@Api(tags = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/{id}")
    @ApiOperation("通过文章id获取分页评论")
    public Result<PageResult> page(@PathVariable Integer id, CommentPageQueryDTO commentPageQueryDTO) {
        PageResult pageResult = commentService.pageQuery(id,commentPageQueryDTO);
        return Result.success(pageResult);
    }

}
