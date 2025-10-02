package com.qd.mapper;

import com.qd.entity.Tag;
import com.qd.vo.TagOverviewVO;
import com.qd.vo.TagVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * 查看标签总览
     * @return
     */
    List<TagOverviewVO> getOverview();

    /**
     * 管理员查看标签总览
     * @return
     */
    List<TagVO> list();

    /**
     * 新增标签
     * @param tag
     */
    @Insert("INSERT INTO tag (name, slug, article_count, status, remark, created_at, updated_at, deleted_at) " +
            "VALUES (#{name}, #{slug}, #{articleCount}, #{status}, #{remark}, #{createdAt}, #{updatedAt}, #{deletedAt})")
    void insert(Tag tag);

    /**
     * 修改标签
     * @param tag
     */
    void update(Tag tag);

    /**
     * 删除标签
     * @param id
     */
    @Delete("DELETE FROM tag WHERE id = #{id}")
    void delete(Long id);
}
