package com.qd.controller.user;

import com.qd.constant.JwtClaimsConstant;
import com.qd.context.BaseContext;
import com.qd.dto.UserLoginDTO1;
import com.qd.entity.User;
import com.qd.entity.User1;
import com.qd.entity.UserUpdateDTO;
import com.qd.properties.JwtProperties;
import com.qd.result.Result;
import com.qd.service.UserService;
import com.qd.utils.JwtUtil;
import com.qd.vo.UserLoginVO;
import com.qd.vo.UserLoginVO1;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;


    @GetMapping("/me")
    @ApiOperation("查看用户个人信息")
    public Result<UserLoginVO> getUserByToken() {
        Long id = BaseContext.getCurrentId();
        UserLoginVO userLoginVO = userService.getById(id);
        return Result.success(userLoginVO);
    }


    @PutMapping("/me")
    @ApiOperation("修改个人基本信息")
    public Result updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user=new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        user.setId(BaseContext.getCurrentId());
        user.setUpdatedAt(LocalDateTime.now());
        userService.update(user);
        return Result.success();
    }

    @PutMapping("/me/avatar")
    @ApiOperation("修改头像")
    public Result updateAvatar(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user=new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        user.setId(BaseContext.getCurrentId());
        user.setUpdatedAt(LocalDateTime.now());
        userService.updateAvatar(user);
        return Result.success();
    }

//
//    @ApiOperation("用户登陆")
//    @PostMapping("/login")
//    public Result<UserLoginVO1> login(@RequestBody UserLoginDTO1 userLoginDTO) {
//
//        User1 user = userService.wxLogin(userLoginDTO);
//
//        //生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.USER_ID, user.getId());
//        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
//
//        UserLoginVO1 userLoginVO = UserLoginVO1.builder()
//                .id(user.getId())
//                .openid(user.getOpenid())
//                .token(token)
//                .build();
//
//        return Result.success(userLoginVO);
//    }

}
