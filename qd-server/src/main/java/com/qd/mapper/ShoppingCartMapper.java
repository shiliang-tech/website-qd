package com.qd.mapper;

import com.qd.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {


    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据id修改商品数量
     * @param shoppingCart
     */
    @Update("UPDATE shopping_cart SET number = #{number} WHERE id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);


    /**
     * 插入购物车数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            "values (#{name}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{image}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据用户id删除购物车数据
     * @param userId
     */
    @Delete("DELETE FROM shopping_cart WHERE user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     * 根据用户id和菜品id删除购物车数据
     * @param cart
     */
    @Delete("DELETE FROM shopping_cart WHERE user_id = #{userId} AND dish_id = #{dishId}")
    void deleteByUserIdAndDishId(ShoppingCart cart);

    /**
     * 根据用户id和套餐id删除购物车数据
     * @param cart
     */
    @Delete("DELETE FROM shopping_cart WHERE user_id = #{userId} AND setmeal_id = #{setmealId}")
    void deleteByUserIdAndSetmealId(ShoppingCart cart);
}
