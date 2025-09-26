package com.qd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "用户登录返回的数据格式")
public class UserLoginVO  implements Serializable {

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("登录名（唯一）")
    private String username;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像URL")
    private String avatarUrl;

    @ApiModelProperty("角色：VISITOR / EDITOR / ADMIN")
    private String role;

    @ApiModelProperty("角色等级（ADMIN=1, EDITOR=5, VISITOR=10）")
    private Integer roleLevel;

    @ApiModelProperty("用户状态：1=启用,0=禁用")
    private Integer status;

    @ApiModelProperty("上次登录时间")
    private LocalDateTime lastLoginAt;

    @ApiModelProperty("jwt令牌")
    private String token;
}
