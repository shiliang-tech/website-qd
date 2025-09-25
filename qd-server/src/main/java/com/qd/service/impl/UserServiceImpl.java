package com.qd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qd.constant.MessageConstant;
import com.qd.dto.UserLoginDTO;
import com.qd.entity.User;
import com.qd.exception.LoginFailedException;
import com.qd.mapper.UserMapper;
import com.qd.properties.WeChatProperties;
import com.qd.service.UserService;
import com.qd.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    //微信登录接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;
    /**
     * 微信登陆
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());
        //判断openid是否为空
        if (openid == null || openid.equals("")) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //判断是否为新用户
        User user = userMapper.getByOPenid(openid);
        //如果是新用户，自动完成注册
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.createUser(user);
        }
        //返回这个用户对象
        return user;
    }

    private String getOpenid(String code) {
        //调用wx接口服务，获得当前用户的openid
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }


}
