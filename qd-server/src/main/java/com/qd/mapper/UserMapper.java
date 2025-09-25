package com.qd.mapper;

import com.qd.entity.User;
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
    User getByOPenid(String openid);

    /**
     * 插入新用户
     * @param user
     */
    void createUser(User user);

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getById(Long userId);


    /**
     * 根据动态条件统计用户数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
