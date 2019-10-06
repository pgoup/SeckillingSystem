package com.application.seckilling.order.service.impl;

import com.application.SeckillingSystem.BaseModule.dao.OrderFormDao;
import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import com.application.seckilling.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author PG
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderFormDao orderFormDao;

    @Override
    public List<OrderForm> queryAllOrder(String userId) {
        return orderFormDao.queryByUserId(userId);
    }

    @Override
    public OrderForm queryOrder(String orderId) {
        return orderFormDao.queryDistinctByOrderId(orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderFormDao.deleteByOrderId(orderId);
    }
}
