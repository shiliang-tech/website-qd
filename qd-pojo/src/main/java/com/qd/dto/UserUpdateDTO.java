package com.qd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO implements Serializable {
    // 登录名，唯一
    private String username;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 昵称
    private String nickname;
    // 头像URL
    private String avatarUrl;
}
