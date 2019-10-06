package com.application.seckilling.order.service;

import com.application.SeckillingSystem.BaseModule.entity.OrderForm;

import java.util.List;

/**
 * @author PG
 */
public interface OrderService {

    /**
     * 查询所有的订单
     *
     * @return
     */
    List<OrderForm> queryAllOrder(String userId);

    /**
     * 查询某个订单详情
     *
     * @param orderId
     * @return
     */
    OrderForm queryOrder(String orderId);

    /**
     * 删除某个订单
     *
     * @param orderId
     * @return
     */
    void deleteOrder(String orderId);
}
