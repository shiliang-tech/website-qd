package com.qd.service;

import com.qd.dto.BgmDTO;
import com.qd.entity.Bgm;
import com.qd.vo.BgmVO;

import java.util.List;

public interface BgmService {
    /**
     * 添加bgm
     * @param bgmDTO
     */
    void addBgm(BgmDTO bgmDTO);

    /**
     * 管理员查看bgm列表
     * @return
     */
    List<BgmVO> list();

    /**
     * 修改bgm信息
     * @param bgmDTO
     */
    void updateBgm(BgmDTO bgmDTO);

    /**
     * 删除bgm
     * @param bgmDTO
     */
    void deleteBgm(BgmDTO bgmDTO);

    /**
     * 用户查看bgm列表
     * @return
     */
    List<BgmVO> getOverview();
}
