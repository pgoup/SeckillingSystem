package com.application.seckillingsystem.shoppingcart.controller;

import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import com.application.SeckillingSystem.BaseModule.entity.ShoppingCart;
import com.application.seckillingsystem.shoppingcart.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * @author PG
 */
@Controller
public class InfoController {

    @Resource
    private ShoppingCartService cartService;

    @RequestMapping(value = "queryByUserId")
    @ResponseBody
    public ShoppingCart queryByUserId() {
        return cartService.queryByUserId("123456789");
    }

    @RequestMapping(value = "addGood")
    public boolean addGood(OrderDetail detail) {
        return cartService.addOrderDetail("123456789", detail);
    }

    @RequestMapping(value = "deleteOrderDetail")
    public boolean deleteGood(OrderDetail detail) {
        return cartService.deleteOrderDetail("123456789", detail);
    }

    @RequestMapping(value = "purchaseAll")
    public boolean purchaseAll(){
        return cartService.purchaseAll();
    }


}
