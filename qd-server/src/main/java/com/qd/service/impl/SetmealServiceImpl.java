package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.constant.MessageConstant;
import com.qd.constant.StatusConstant;
import com.qd.context.BaseContext;
import com.qd.dto.SetmealDTO;
import com.qd.dto.SetmealPageQueryDTO;
import com.qd.entity.Setmeal;
import com.qd.entity.SetmealDish;
import com.qd.exception.DeletionNotAllowedException;
import com.qd.mapper.SetMealDishMapper;
import com.qd.mapper.SetmealMapper;
import com.qd.result.PageResult;
import com.qd.service.SetmealService;
import com.qd.vo.DishItemVO;
import com.qd.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private SetmealService setmealService;


    /**
     * 新增套餐
     * @param setmealDTO
     */
    public void saveSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //向套餐表插入一条数据
        setmealMapper.insert(setmeal);
        //获取insert语句获取的主键值
        Long id = setmeal.getId();
        //向套餐-菜品表插入n条数据
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0) {
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(id);
            });
            //向套餐-菜品表插入n条数据
            setMealDishMapper.insertBatch(setmealDishes);
        }

    }

    /**
     * 修改套餐
     * @param setmealDTO
     */
    public void updateSetmeal(SetmealDTO setmealDTO) {
        //修改套餐信息
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //修改套餐表基本信息
        setmealMapper.updateSetmeal(setmeal);
        //删除原有的套餐-菜品数据
        setMealDishMapper.deleteBySetmealId(setmeal.getId());
        //添加新的套餐-菜品数据
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0) {
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmeal.getId());
            });
            //向套餐-菜品表插入n条数据
            setMealDishMapper.insertBatch(setmealDishes);
        }
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    public SetmealVO getSetmealById(Long id) {
        SetmealVO setmealVO = setmealMapper.queryById(id);
        return setmealVO;
    }

    /**
     * 根据套餐id查询菜品信息
     * @param id
     * @return
     */
    public List<SetmealDish> getSetmealDishById(Long id) {
        List<SetmealDish> setmealDishes = setMealDishMapper.getSetmealDishes(id);
        return setmealDishes;
    }

    /**
     * 套餐批量删除
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //判断是否能删除--是否存在启售中的套餐？？？
        for (Long id : ids) {
            SetmealVO setmealVO = setmealMapper.queryById(id);
            if (setmealVO.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //根据套餐id集合批量删除套餐数据
        setmealMapper.deleteByIds(ids);
        //根据套餐id集合批量删除套餐-菜品数据
        setMealDishMapper.deleteBySetmealIds(ids);
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
            Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        setmealMapper.updateSetmeal(setmeal);
    }

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }

}
