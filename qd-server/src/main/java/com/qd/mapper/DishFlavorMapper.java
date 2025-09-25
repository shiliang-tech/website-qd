package com.qd.mapper;

import com.qd.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除口味数据
     * @param dishId
     */
    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品集合批量删除口味
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据id查询菜品和对应口味数据
     * @param id
     * @return
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id=#{dishId}")
    List<DishFlavor> getByDishId(Long id);
}
