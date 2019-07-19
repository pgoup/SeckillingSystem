package com.application.service;

public interface GoodService
{
    /**
     * 用户id购买数量为count的商品
     * @param goodId
     * @param count
     * @return
     */
    public  boolean purchaseGood(int goodId,int count);
}
