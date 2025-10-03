package com.qd.mapper;

import com.qd.entity.Bgm;
import com.qd.vo.BgmVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BgmMapper {

    /**
     * 添加bgm
     * @param bgm
     */
    @Insert("INSERT INTO bgm (title, artist, audio_url, sort, status, remark, created_at, updated_at) " +
            "VALUES (#{title}, #{artist}, #{audioUrl}, #{sort}, #{status}, #{remark}, #{createdAt}, #{updatedAt})")
    void insert(Bgm bgm);

    /**
     * 管理员查看bgm列表
     * @return
     */
    @Select("SELECT * FROM bgm ORDER BY sort ASC ")
    List<BgmVO> list();

    /**
     * 修改bgm信息
     * @param bgm
     */
    void update(Bgm bgm);

    /**
     * 根据id删除bgm
     *
     * @param id
     */
    @Delete("DELETE FROM bgm WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 用户查看bgm列表
     * @return
     */
    List<BgmVO> getOverview();
}
