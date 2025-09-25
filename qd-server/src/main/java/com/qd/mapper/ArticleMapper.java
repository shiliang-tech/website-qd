package com.qd.mapper;
import com.github.pagehelper.Page;
import com.qd.dto.ArticlePageQueryDTO;
import com.qd.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    /**
     * 文章分页查询
     * @param articlePageQueryDTO
     * @return
     */
    Page<ArticleVO> pageQuery(ArticlePageQueryDTO articlePageQueryDTO);
}
