package com.qd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qd.constant.MessageConstant;
import com.qd.constant.StatusConstant;
import com.qd.context.BaseContext;
import com.qd.dto.UserLoginDTO;
import com.qd.dto.UserLoginDTO1;
import com.qd.dto.UserRegisterDTO;
import com.qd.entity.Employee;
import com.qd.entity.User;
import com.qd.entity.User1;
import com.qd.exception.*;
import com.qd.mapper.UserMapper;
import com.qd.properties.WeChatProperties;
import com.qd.service.UserService;
import com.qd.utils.HttpClientUtil;
import com.qd.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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
    public User1 wxLogin(UserLoginDTO1 userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());
        //判断openid是否为空
        if (openid == null || openid.equals("")) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //判断是否为新用户
        User1 user = userMapper.getByOPenid(openid);
        //如果是新用户，自动完成注册
//        if (user == null) {
//            user = User1.builder()
//                    .openid(openid)
//                    .createTime(LocalDateTime.now())
//                    .build();
//            userMapper.createUser(user);
//        }
        //返回这个用户对象
        return user;
    }

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     */
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        String email = user.getEmail();
        String prefix = (email != null && email.contains("@"))
                ? email.substring(0, email.indexOf("@"))
                : "user";
        user.setUsername(prefix + "_" + System.currentTimeMillis());
        user.setPasswordHash(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRoleLevel(10);
        user.setStatus(1);
        user.setRole("VISITOR");
        userMapper.createUser(user);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();

        //1、根据邮箱查询数据库中的数据
        User user = userMapper.getByEmail(email);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPasswordHash())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        user.setLastLoginAt(LocalDateTime.now());
        //TODO 后期需要添加用户ip逻辑
//        user.setLastLoginIp();
        //3、返回实体对象
        return user;
    }

    /**
     * 根据uid获取个人信息
     * @param id
     * @return
     */
    public UserLoginVO getById(Long id) {
        User user = userMapper.getById(id);
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        return userLoginVO;
    }

    /**
     * 修改个人基本信息
     * @param user
     */
    @Transactional
    public void update(User user) {
        User result = userMapper.getByName(user.getUsername());
        if (result != null && result.getId() != user.getId()) {
            throw new UserUpdateNotAllowedException(MessageConstant.USERNAME_EXISTS);
        }
        userMapper.update(user);
    }

    /**
     * 修改个人头像
     * @param user
     */
    public void updateAvatar(User user) {
        userMapper.update(user);
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
