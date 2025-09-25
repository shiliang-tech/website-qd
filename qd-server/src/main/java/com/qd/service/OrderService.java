package com.qd.service;

import com.qd.dto.*;
import com.qd.result.PageResult;
import com.qd.vo.OrderPaymentVO;
import com.qd.vo.OrderStatisticsVO;
import com.qd.vo.OrderSubmitVO;
import com.qd.vo.OrderVO;

public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 用户历史订单查询
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 订单详情查询
     * @param id
     * @return
     */
    OrderVO getOrderById(Long id);

    /**
     * 取消订单
     * @param id
     */
    void deleteOrder(Long id);

    /**
     * 再来一单
     * @param id
     */
    void againOrder(Long id);


    /**
     * 管理端订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult pageQueryAdmin(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     * @return
     */
    OrderStatisticsVO statistics();

    /**
     * 接单
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * @param ordersRejectionDTO
     */
    void reject(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);


    /**
     * 派送订单
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成订单
     * @param id
     */
    void complete(Long id);

    /**
     * 用户催单
     * @param id
     */
    void reminder(Long id);
}
