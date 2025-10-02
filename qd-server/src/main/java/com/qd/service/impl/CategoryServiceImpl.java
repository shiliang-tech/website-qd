package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.constant.MessageConstant;
import com.qd.constant.StatusConstant;
import com.qd.context.BaseContext;
import com.qd.dto.CategoryDTO;
import com.qd.dto.CategoryPageQueryDTO;
import com.qd.entity.Category;
import com.qd.exception.DeletionNotAllowedException;
import com.qd.mapper.CategoryMapper;
import com.qd.mapper.DishMapper;
import com.qd.mapper.SetmealMapper;
import com.qd.result.PageResult;
import com.qd.service.CategoryService;
import com.qd.utils.SlugUtil;
import com.qd.vo.CategoryOverviewVO;
import com.qd.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类业务层
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 新增分类
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //分类状态默认为禁用状态0
        category.setStatus(StatusConstant.DISABLE);
        category.setArticleCount(0);
        category.setSort(findMaxSort() + 1);
        category.setSlug(SlugUtil.slugifyWithPinyin(category.getName()));
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        categoryMapper.insert(category);
    }

    private Integer findMaxSort() {
        List<CategoryVO> list = categoryMapper.list();
        Integer maxSort = 0;
        for (CategoryVO categoryVO : list) {
            if (categoryVO.getSort() > maxSort) {
                maxSort = categoryVO.getSort();
            }
        }
        return maxSort;
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        //下一条sql进行分页，自动加入limit关键字分页
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id删除分类
     * @param id
     */
    public void deleteById(Long id) {
        //删除分类数据
        categoryMapper.deleteById(id);
    }

    /**
     * 修改分类
     *
     * @param categoryDTO
     */
    public void update(Long id, CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setId(id);
        category.setSlug(SlugUtil.slugifyWithPinyin(categoryDTO.getName()));
        category.setUpdatedAt(LocalDateTime.now());


        categoryMapper.update(category);
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
//                .updateTime(LocalDateTime.now())
//                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * 管理员查看分类概况
     * @return
     */
    public List<CategoryVO> list() {
        return categoryMapper.list();
    }

    /**
     * 查询分类概况
     * @return
     */
    public List<CategoryOverviewVO> getOverview() {
        return categoryMapper.getOverview();
    }
}
