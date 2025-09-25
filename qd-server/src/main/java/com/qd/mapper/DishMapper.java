package com.qd.mapper;

import com.github.pagehelper.Page;
import com.qd.annotation.AutoFill;
import com.qd.dto.DishPageQueryDTO;
import com.qd.entity.Dish;
import com.qd.enumeration.OperationType;
import com.qd.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据主键查询菜品
     * @param id
     * @return
     */
    @Select("SELECT * FROM dish WHERE id =#{id}")
    Dish getById(Long id);

    @ApiOperation("根据主键查询菜品数据")
    @Delete("DELETE FROM dish WHERE id =#{id}")
    void deleteById(Long id);

    /**
     * 根据list集合批量删除菜品
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据id修改菜品基本信息
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据类型主键查询菜品
     * @param id
     * @return
     */
    List<Dish> getByCategoryId(Long id);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(HashMap map);
}
