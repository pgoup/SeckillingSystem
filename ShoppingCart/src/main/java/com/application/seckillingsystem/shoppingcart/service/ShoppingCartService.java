package com.application.seckillingsystem.shoppingcart.service;

import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import com.application.SeckillingSystem.BaseModule.entity.ShoppingCart;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**
 * @author PG
 */
public interface ShoppingCartService {

    ShoppingCart queryByUserId(String userId);

    boolean addOrderDetail(String userId, OrderDetail orderDetail);

    boolean deleteOrderDetail(String userId, OrderDetail orderDetail);

    boolean purchaseAll();
}
