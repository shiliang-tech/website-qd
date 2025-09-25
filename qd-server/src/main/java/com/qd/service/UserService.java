package com.qd.service;

import com.qd.dto.UserLoginDTO;
import com.qd.entity.User;


public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);

}
