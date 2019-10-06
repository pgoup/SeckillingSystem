package com.application.seckilling.order.controller;

import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import com.application.seckilling.order.service.OrderService;
import org.hibernate.sql.ordering.antlr.OrderByFragment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author PG
 */
@Controller
public class OrderInfoController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/queryAllOrders")
    public List<OrderForm> queryAllOrderForm() {
        String userId = "8998989";
        return orderService.queryAllOrder(userId);
    }

    @GetMapping(value = "/queryOrderDetail")
    public OrderForm queryOrderDetail(@PathParam("OrderId") String orderFormId) {
        return orderService.queryOrder(orderFormId);
    }

    @GetMapping(value = "/deleteOrder")
    public String deleteOrder(@PathParam("OrderId") String orderId) {
        orderService.deleteOrder(orderId);
        return "ds";
    }
}
