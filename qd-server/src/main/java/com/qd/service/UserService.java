package com.qd.service;

import com.qd.dto.UserLoginDTO;
import com.qd.dto.UserLoginDTO1;
import com.qd.dto.UserRegisterDTO;
import com.qd.entity.User;
import com.qd.entity.User1;
import com.qd.vo.UserLoginVO;


public interface UserService {

    User1 wxLogin(UserLoginDTO1 userLoginDTO);

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 根据uid获取个人信息
     * @param id
     * @return
     */
    UserLoginVO getById(Long id);
}
