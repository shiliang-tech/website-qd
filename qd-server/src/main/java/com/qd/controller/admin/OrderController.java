package com.qd.controller.admin;

import com.qd.dto.OrdersCancelDTO;
import com.qd.dto.OrdersConfirmDTO;
import com.qd.dto.OrdersPageQueryDTO;
import com.qd.dto.OrdersRejectionDTO;
import com.qd.result.PageResult;
import com.qd.result.Result;
import com.qd.service.OrderService;
import com.qd.vo.OrderStatisticsVO;
import com.qd.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Api(tags = "用户订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> get(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.pageQueryAdmin(ordersPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 各个状态的订单数量统计
     * @return
     */
    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }


    /**
     * 查看订单详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public Result<OrderVO> details(@PathVariable Long id) {
        OrderVO orderVO = orderService.getOrderById(id);
        return Result.success(orderVO);
    }

    /**
     * 接单
     * @param ordersConfirmDTO
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 拒单
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result reject(@RequestBody OrdersRejectionDTO ordersRejectionDTO) {
        orderService.reject(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 取消订单
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result reject(@RequestBody OrdersCancelDTO ordersCancelDTO) {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 派送订单
     * @param id
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result complete(@PathVariable Long id) {
        orderService.complete(id);
        return Result.success();
    }

}
