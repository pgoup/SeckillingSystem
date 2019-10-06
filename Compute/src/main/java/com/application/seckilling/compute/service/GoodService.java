package com.application.seckilling.compute.service;

import com.application.SeckillingSystem.BaseModule.entity.Good;
import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import com.application.SeckillingSystem.BaseModule.entity.OrderForm;

public interface GoodService {
    /**
     * 购买商品
     *
     * @param goodId
     * @param count
     * @return
     */
    boolean purchaseGood(String userId, String goodId, int count, OrderForm orderForm);
}
