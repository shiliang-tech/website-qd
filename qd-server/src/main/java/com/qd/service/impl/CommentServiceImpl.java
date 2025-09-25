package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.dto.CommentPageQueryDTO;
import com.qd.mapper.CommentMapper;
import com.qd.result.PageResult;
import com.qd.service.CommentService;
import com.qd.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据文章id获取评论
     * @param id
     * @param commentPageQueryDTO
     * @return
     */
    public PageResult pageQuery(Integer id, CommentPageQueryDTO commentPageQueryDTO) {
        // 1. 分页查根评论
        PageHelper.startPage(commentPageQueryDTO.getPage(), commentPageQueryDTO.getSize());
        Page<CommentVO> page = commentMapper.pageQuery(id, commentPageQueryDTO);

        List<CommentVO> rootComments = page.getResult();
        if (rootComments.isEmpty()) {
            return new PageResult(page.getTotal(), rootComments);
        }
        // 2. 收集根评论ID
        List<Long> rootIds = rootComments.stream()
                .map(CommentVO::getId)
                .collect(Collectors.toList());
        // 3. 批量查询所有子评论
        List<CommentVO> childComments = commentMapper.findByRootIds(rootIds);
        // 4. 按 rootId 分组
        Map<Long, List<CommentVO>> childrenMap = childComments.stream()
                .collect(Collectors.groupingBy(CommentVO::getRootId));
        // 5. 塞回 children 字段
        for (CommentVO root : rootComments) {
            List<CommentVO> children = childrenMap.getOrDefault(root.getId(), new ArrayList<>());
            root.setChildren(children);
        }

        return new PageResult(page.getTotal(), rootComments);
    }
}
