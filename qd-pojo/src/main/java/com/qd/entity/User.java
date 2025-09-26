package com.qd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 用户ID
    private Long id;
    // 登录名，唯一
    private String username;
    // 密码哈希（BCrypt）
    private String passwordHash;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 昵称
    private String nickname;
    // 头像URL
    private String avatarUrl;
    // 角色：VISITOR / EDITOR / ADMIN
    private String role;
    // 角色等级（ADMIN=1, EDITOR=5, VISITOR=10）
    private Integer roleLevel;
    // 用户状态：1=启用,0=禁用
    private Integer status;
    // 上次登录时间
    private LocalDateTime lastLoginAt;
    // 上次登录IP
    private String lastLoginIp;
    // 扩展信息（JSON字符串）
    private String extra;
    // 备注
    private String remark;
    // 创建时间
    private LocalDateTime createdAt;
    // 更新时间
    private LocalDateTime updatedAt;
    // 删除时间（逻辑删除）
    private LocalDateTime deletedAt;
}
