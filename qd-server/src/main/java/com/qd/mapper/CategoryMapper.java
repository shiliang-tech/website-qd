package com.qd.mapper;

import com.github.pagehelper.Page;
import com.qd.annotation.AutoFill;
import com.qd.dto.CategoryPageQueryDTO;
import com.qd.entity.Category;
import com.qd.enumeration.OperationType;
import com.qd.vo.CategoryOverviewVO;
import com.qd.vo.CategoryVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入数据
     * @param category
     */
    @Insert("INSERT INTO category (name, slug, sort, article_count, status, remark, created_at, updated_at, deleted_at) " +
            "VALUES (#{name}, #{slug}, #{sort}, #{articleCount}, #{status}, #{remark}, #{createdAt}, #{updatedAt}, #{deletedAt})")
    void insert(Category category);

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id修改分类
     * @param category
     */
    void update(Category category);

    /**
     * 管理员查看分类概况
     * @return
     */
    List<CategoryVO> list();

    /**
     * 查询分类概况
     * @return
     */
    List<CategoryOverviewVO> getOverview();
}
