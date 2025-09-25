package com.qd.mapper;

import com.github.pagehelper.Page;
import com.qd.dto.CommentPageQueryDTO;
import com.qd.entity.Comment;
import com.qd.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 根据文章id获取分页根评论
     *
     * @param id
     * @return
     */
    Page<CommentVO> pageQuery(Integer id, CommentPageQueryDTO commentPageQueryDTO);

    /**
     * 查询子评论
     * @param rootIds
     * @return
     */
    List<CommentVO> findByRootIds(List<Long> rootIds);
}
