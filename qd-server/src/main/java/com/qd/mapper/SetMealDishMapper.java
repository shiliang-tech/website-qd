package com.qd.mapper;

import com.qd.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetMealDishIds(List<Long> dishIds);


    /**
     * 批量插入套餐-菜品数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id查询对应的菜品信息
     * @param id
     * @return
     */
    List<SetmealDish> getSetmealDishes(Long id);

    /**
     * 根据套餐id批量删除对应的套餐-菜品信息
     * @param setmealIds
     */
    void deleteBySetmealIds(List<Long> setmealIds);

    /**
     * 根据套餐id删除对应的套餐-菜品信息
     * @param id
     */
    @Delete("Delete FROM setmeal_dish WHERE setmeal_id= #{id} ")
    void deleteBySetmealId(Long id);
}
