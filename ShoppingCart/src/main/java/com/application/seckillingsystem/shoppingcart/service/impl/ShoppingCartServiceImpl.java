package com.application.seckillingsystem.shoppingcart.service.impl;

import com.application.SeckillingSystem.BaseModule.dao.GoodDao;
import com.application.SeckillingSystem.BaseModule.dao.OrderFormDao;
import com.application.SeckillingSystem.BaseModule.dao.ShoppingCartDao;
import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import com.application.SeckillingSystem.BaseModule.entity.ShoppingCart;
import com.application.SeckillingSystem.BaseModule.entity.User;
import com.application.seckillingsystem.shoppingcart.service.SOAComputeService;
import com.application.seckillingsystem.shoppingcart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * @author PG
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartDao cartDao;

    @Resource
    private GoodDao goodDao;

    @Resource
    private OrderFormDao formDao;

    @Resource
    private SOAComputeService computeService;

    private Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Override
    public ShoppingCart queryByUserId(String userId) {
        return cartDao.queryByUserId(userId);
    }

    @Override
    public boolean addOrderDetail(String userId, OrderDetail orderDetail) {
        ShoppingCart shoppingCart = cartDao.queryByUserId(userId);
        shoppingCart.getOrderDetails().add(orderDetail);
        cartDao.saveAndFlush(shoppingCart);
        return true;
    }

    @Override
    public boolean deleteOrderDetail(String userId, OrderDetail orderDetail) {
        ShoppingCart shoppingCart = cartDao.queryByUserId(userId);
        shoppingCart.getOrderDetails().remove(orderDetail);
        cartDao.saveAndFlush(shoppingCart);
        return true;
    }

    public boolean deleteAllOrderDetails(String userId) {
        ShoppingCart shoppingCart = cartDao.queryByUserId(userId);
        shoppingCart.getOrderDetails().clear();
        cartDao.saveAndFlush(shoppingCart);
        return true;
    }

    @Override
    public boolean purchaseAll() {
        User user = new User();
        ShoppingCart cart = cartDao.queryByUserId(user.getUserId());
        Set<OrderDetail> details = cart.getOrderDetails();
        Iterator<OrderDetail> detail = details.iterator();
        while (detail.hasNext()) {
            OrderDetail orderDetail = detail.next();
            if (orderDetail.getCount() < goodDao.queryDistinctCountByGoodId(orderDetail.getGoodId()))
                logger.info("商品" + orderDetail.getGoodName() + "库存不足，无法购买！");
            return false;
        }
        detail = details.iterator();
        OrderForm orderForm = new OrderForm();
        while (detail.hasNext()) {
            OrderDetail orderDetail = detail.next();
            //单个订单完成减库操作
            computeService.purchaseGood(user.getUserId(), orderDetail.getGoodId(), orderDetail.getCount(), orderForm);
        }
        formDao.save(orderForm);
        deleteAllOrderDetails(user.getUserId());
        return true;
    }


}
