package com.qd.service.impl;

import com.qd.dto.BgmDTO;
import com.qd.entity.Bgm;
import com.qd.mapper.BgmMapper;
import com.qd.service.BgmService;
import com.qd.vo.BgmVO;
import com.qd.vo.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    private BgmMapper bgmMapper;

    /**
     * 添加bgm
     * @param bgmDTO
     */
    public void addBgm(BgmDTO bgmDTO) {
        Bgm bgm = new Bgm();
        BeanUtils.copyProperties(bgmDTO, bgm);
        bgm.setStatus(0);
        bgm.setCreatedAt(LocalDateTime.now());
        bgm.setUpdatedAt(LocalDateTime.now());
        bgm.setSort(findMaxSort() + 1);
        bgmMapper.insert(bgm);
    }

    private Integer findMaxSort() {
        List<BgmVO> list = bgmMapper.list();
        Integer maxSort = 0;
        for (BgmVO bgmVO : list) {
            if (bgmVO.getSort() > maxSort) {
                maxSort = bgmVO.getSort();
            }
        }
        return maxSort;
    }

    /**
     * 管理员查看bgm列表
     * @return
     */
    public List<BgmVO> list() {
        return bgmMapper.list();
    }

    /**
     * 修改bgm信息
     * @param bgmDTO
     */
    public void updateBgm(BgmDTO bgmDTO) {
        Bgm bgm = new Bgm();
        BeanUtils.copyProperties(bgmDTO, bgm);
        bgm.setUpdatedAt(LocalDateTime.now());
        bgmMapper.update(bgm);
    }

    /**
     * 删除bgm
     * @param bgmDTO
     */
    public void deleteBgm(BgmDTO bgmDTO) {
        bgmMapper.deleteById(bgmDTO.getId());
    }

    /**
     * 用户获取bgm列表
     * @return
     */
    public List<BgmVO> getOverview() {
        return bgmMapper.getOverview();
    }
}
