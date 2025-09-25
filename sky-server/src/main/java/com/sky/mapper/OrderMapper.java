package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 历史订单分页查询
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 订单详情查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM orders WHERE id = #{id}")
    Orders getById(Long id);

    /**
     * 取消订单
     * @param id
     */
    @Update("UPDATE orders SET status = 6 WHERE id = #{id};")
    void deleteOrder(Long id);


    /**
     * 各个状态的订单数量统计：待接单
     * @return
     */
    @Select("SELECT COUNT(*) FROM orders WHERE status = 2")
    Integer toBeConfirmed();

    /**
     * 各个状态的订单数量统计：待派送
     * @return
     */
    @Select("SELECT COUNT(*) FROM orders WHERE status = 3")
    Integer confirmed();


    /**
     * 各个状态的订单数量统计：派送中
     * @return
     */
    @Select("SELECT COUNT(*) FROM orders WHERE status = 4")
    Integer deliveryInProgress();

    /**
     * 接单
     * @param id
     */
    @Update("UPDATE orders SET status = 3 WHERE id = #{id};")
    void confirm(Long id);

    /**
     * 根据订单状态和下单时间查询订单
     * @param status
     * @param orderTime
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);


    /**
     * 用于替换微信支付更新数据库状态的问题
     * @param orderStatus
     * @param orderPaidStatus
     */
    @Update("update orders set status = #{orderStatus},pay_status = #{orderPaidStatus} ,checkout_time = #{check_out_time} where id = #{id}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime check_out_time, Long id);

    /**
     * 根据动态条件统计营业额数据
     * @param hashMap
     * @return
     */
    Double sumByMap(HashMap hashMap);

    /**
     * 根据订单条件统计订单数量
     * @param hashMap
     * @return
     */
    Integer countByMap(HashMap hashMap);

    /**
     * 统计指定时间区间内的销量前十
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getSalesTop(LocalDateTime begin, LocalDateTime end);


}
