package com.qd.controller.user;

import com.qd.dto.CommentDTO;
import com.qd.result.Result;
import com.qd.service.ArticleService;
import com.qd.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userArticleController")
@RequestMapping("/articles")
@Slf4j
@Api(tags = "用户发帖、回复相关接口")
public class ArticleController {


}
