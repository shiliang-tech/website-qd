package com.qd.mapper;
import com.github.pagehelper.Page;
import com.qd.dto.ArticlePageQueryDTO;
import com.qd.vo.ArticleDetailVO;
import com.qd.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {

    /**
     * 文章分页查询
     * @param articlePageQueryDTO
     * @return
     */
    Page<ArticleVO> pageQuery(ArticlePageQueryDTO articlePageQueryDTO);

    /**
     * 根据id查询文章详情内容
     * @param id
     * @return
     */
    @Select("SELECT * FROM article WHERE id = #{id}")
    ArticleDetailVO getById(long id);
}
