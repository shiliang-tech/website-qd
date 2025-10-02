package com.qd.service;

import com.qd.dto.TagDTO;
import com.qd.vo.TagOverviewVO;
import com.qd.vo.TagVO;

import java.util.List;

public interface TagService {

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
     * @param tagDTO
     */
    void createTag(TagDTO tagDTO);

    /**
     * updateTag
     * @param tagDTO
     */
    void updateTag(TagDTO tagDTO);

    /**
     * 删除标签
     * @param tagDTO
     */
    void deleteTag(TagDTO tagDTO);
}
