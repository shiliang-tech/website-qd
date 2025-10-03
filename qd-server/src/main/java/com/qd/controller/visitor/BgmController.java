package com.qd.controller.visitor;

import com.qd.result.Result;
import com.qd.service.BgmService;
import com.qd.vo.BgmVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bgms")
@Api(tags = "用户获取bgm接口")
@Slf4j
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @GetMapping
    @ApiOperation("用户获取bgm")
    public Result<List<BgmVO>> getOverview() {
        List<BgmVO> list = bgmService.getOverview();
        return Result.success(list);
    }
}
