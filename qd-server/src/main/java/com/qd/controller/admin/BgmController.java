package com.qd.controller.admin;

import com.qd.dto.BgmDTO;
import com.qd.entity.Bgm;
import com.qd.result.Result;
import com.qd.service.BgmService;
import com.qd.vo.BgmVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("adminBgmController")
@RequestMapping("/admin/bgms")
@Api(tags = "管理员bgm管理接口")
@Slf4j
public class BgmController {

    @Autowired
    private BgmService bgmService;


    /**
     * 管理员查看bgm列表
     * @return
     */
    @GetMapping
    @ApiOperation("管理员查看bgm列表")
    public Result<List<BgmVO>> list() {
        List<BgmVO> list = bgmService.list();
        return Result.success(list);
    }

    /**
     * 添加bgm
     * @param bgmDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加bgm")
    public Result addBgm(@RequestBody BgmDTO bgmDTO) {
        bgmService.addBgm(bgmDTO);
        return Result.success();
    }


    /**
     * 修改bgm信息
     * @param bgmDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改bgm信息")
    public Result updateBgm(@RequestBody BgmDTO bgmDTO) {
        bgmService.updateBgm(bgmDTO);
        return Result.success();
    }

    /**
     * 删除bgm
     * @param bgmDTO
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除bgm")
    public Result deleteBgm(@RequestBody BgmDTO bgmDTO) {
        bgmService.deleteBgm(bgmDTO);
        return Result.success();
    }
}
