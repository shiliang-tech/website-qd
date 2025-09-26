package com.qd.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qd.constant.MessageConstant;
import com.qd.context.BaseContext;
import com.qd.dto.CommentDTO;
import com.qd.dto.CommentPageQueryDTO;
import com.qd.entity.Comment;
import com.qd.entity.User;
import com.qd.exception.CommentErrorException;
import com.qd.mapper.CommentMapper;
import com.qd.mapper.UserMapper;
import com.qd.result.PageResult;
import com.qd.service.CommentService;
import com.qd.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

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

    /**
     * 在帖子下发表评论
     * @param commentDTO
     */
    public void createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUserId(BaseContext.getCurrentId());
        comment.setLevel(1);
        comment.setStatus(1);
        User user = userMapper.getById(comment.getUserId());
        comment.setNickname(user.getNickname());
        commentMapper.insert(comment);
    }

    /**
     * 对评论进行回复
     * @param commentDTO
     */
    public void reply(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUserId(BaseContext.getCurrentId());
        comment.setLevel(2);
        comment.setStatus(1);
        User user = userMapper.getById(comment.getUserId());
        User repliedUser = userMapper.getById(comment.getReplyToUserId());
        comment.setNickname(user.getNickname());
        comment.setReplyToNickname(repliedUser.getNickname());
        commentMapper.insert(comment);
    }

    @Override
    public void delete(Integer id) {
        Comment comment = commentMapper.getById(id);
        if (comment == null) {
            throw new CommentErrorException(MessageConstant.COMMENT_NOT_FOUND);
        }
        if (comment.getUserId() != BaseContext.getCurrentId()) {
            throw new CommentErrorException(MessageConstant.COMMENT_NOT_YOURS);
        }
        if (comment.getStatus() != 1) {
            throw new CommentErrorException(MessageConstant.COMMENT_STATUS_ERROR);
        }
        Comment updateComment = new Comment();
        updateComment.setId(id.longValue());
        updateComment.setDeletedAt(LocalDateTime.now());
        updateComment.setStatus(2);
        commentMapper.update(updateComment);
    }
}
