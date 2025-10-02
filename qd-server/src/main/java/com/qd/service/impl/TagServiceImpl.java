package com.qd.service.impl;

import com.qd.dto.TagDTO;
import com.qd.entity.Tag;
import com.qd.mapper.TagMapper;
import com.qd.service.TagService;
import com.qd.utils.SlugUtil;
import com.qd.vo.TagOverviewVO;
import com.qd.vo.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 查看标签总览
     * @return
     */
    public List<TagOverviewVO> getOverview() {
        return tagMapper.getOverview();
    }

    /**
     * 管理员查看标签总览
     * @return
     */
    public List<TagVO> list() {
        return tagMapper.list();
    }

    /**
     * 新增标签
     * @param tagDTO
     */
    public void createTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        tag.setSlug(SlugUtil.slugifyWithPinyin(tagDTO.getName()));
        tag.setArticleCount(0);
        tag.setStatus(0);
        tag.setCreatedAt(LocalDateTime.now());
        tag.setUpdatedAt(LocalDateTime.now());

        tagMapper.insert(tag);
    }

    /**
     * updateTag
     * @param tagDTO
     */
    public void updateTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        tag.setUpdatedAt(LocalDateTime.now());
        tag.setSlug(SlugUtil.slugifyWithPinyin(tagDTO.getName()));

        tagMapper.update(tag);
    }

    /**
     * 删除标签
     * @param tagDTO
     */
    public void deleteTag(TagDTO tagDTO) {
        tagMapper.delete(tagDTO.getId());
    }
}
