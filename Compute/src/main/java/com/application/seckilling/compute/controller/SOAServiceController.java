package com.application.seckilling.compute.controller;

import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import com.application.seckilling.compute.service.GoodService;
import com.netflix.client.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author PG
 */
@Controller
public class SOAServiceController {

    @Resource
    private GoodService goodService;

    @PostMapping("/purchaseGood")
    public boolean purchaseGood(@RequestParam("userId") String userId, @RequestParam("goodId") String goodId, @RequestParam("count") int count, @RequestBody OrderForm orderForm) {
        return goodService.purchaseGood(userId, goodId, count, orderForm);
    }
}
