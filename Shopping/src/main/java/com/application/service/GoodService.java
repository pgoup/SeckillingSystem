package com.application.service;

import com.application.entity.Good;

import java.util.List;

public interface GoodService
{
    /**
     * 用户id购买数量为count的商品
     * @param goodId
     * @param count
     * @return
     */
    public  boolean purchaseGood(String goodId,int count);


    /**
     * 根据商品编号查询具体的一个商品
     * @param goodId
     * @return
     */
    public Good queryGoodByGoodId(String goodId);


    /**
     * 根据商品是否打折查询商品
     * @return
     */
    public List<Good> queryGoodByDiscount();

    /**
     * 根据商品类型查找商品
     * @param kind
     * @return
     */
    public List<Good> queryGoodByKind(String kind);

}
