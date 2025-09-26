package com.qd.controller.user;

import com.qd.dto.CommentDTO;
import com.qd.result.Result;
import com.qd.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userCommentController")
@RequestMapping("/comments")
@Slf4j
@Api(tags = "用户评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ApiOperation("用户发表评论")
    public Result createComment(@RequestBody CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
        return Result.success();
    }

    @PostMapping("/replies")
    @ApiOperation("用户回复评论")
    public Result reply(@RequestBody CommentDTO commentDTO) {
        commentService.reply(commentDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除自己的评论")
    public Result delete(@PathVariable Integer id) {
        commentService.delete(id);
        return Result.success();
    }


}
