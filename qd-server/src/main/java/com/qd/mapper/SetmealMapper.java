package com.qd.mapper;

import com.github.pagehelper.Page;
import com.qd.annotation.AutoFill;
import com.qd.dto.SetmealPageQueryDTO;
import com.qd.entity.Setmeal;
import com.qd.enumeration.OperationType;
import com.qd.vo.DishItemVO;
import com.qd.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);


    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 插入套餐数据
     * @param setmeal
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    SetmealVO queryById(Long id);

    /**
     * 根据id批量删除套餐数据
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改套餐
     * @param setmeal
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateSetmeal(Setmeal setmeal);

    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(HashMap map);

}
