package com.application.seckillingsystem.shoppingcart.service;

import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SOAComputeService")
@Service
public interface SOAComputeService {

    /**
     * 调用compute模块的微服务进行子订单的减库操作，同时会将子订单添加至父订单中
     * @param userId
     * @param goodId
     * @param count
     * @param orderForm
     * @return
     */
    @RequestMapping(value = "/Compute/purchaseGood", method = RequestMethod.POST)
    boolean purchaseGood(@RequestParam("userId") String userId, @RequestParam("goodId") String goodId, @RequestParam("count") int count, @RequestBody OrderForm orderForm);
}
