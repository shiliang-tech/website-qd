package com.qd.mapper;

import com.qd.entity.User;
import com.qd.entity.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {



    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User1 getByOPenid(String openid);

    /**
     * 插入新用户
     * @param user
     */
    void createUser(User user);

    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getById(Long userId);


    /**
     * 根据动态条件统计用户数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    @Select("SELECT * FROM user WHERE email=#{email}")
    User getByEmail(String email);
}
