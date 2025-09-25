package com.qd.service;

import com.qd.dto.SetmealDTO;
import com.qd.dto.SetmealPageQueryDTO;
import com.qd.entity.Setmeal;
import com.qd.entity.SetmealDish;
import com.qd.result.PageResult;
import com.qd.vo.DishItemVO;
import com.qd.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveSetmeal(SetmealDTO setmealDTO);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void updateSetmeal(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    SetmealVO getSetmealById(Long id);

    /**
     * 根据套餐id查询套餐菜品信息
     * @param id
     * @return
     */
    List<SetmealDish> getSetmealDishById(Long id);

    /**
     * 套餐批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

}
