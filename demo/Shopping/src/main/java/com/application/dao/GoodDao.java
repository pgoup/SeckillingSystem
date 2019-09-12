package com.application.dao;

import com.application.entity.Good;

import java.util.List;
import java.util.Set;

public interface GoodDao {

    /**
     * 根据商品编号查询具体的一个商品
     * @param goodId
     * @return
     */
    public Good queryGoodByGoodId(String goodId);


    /**
     * 根据商品是否打折查询商品
     * @param discount
     * @return
     */
    public List<Good> queryGoodByDiscount(int discount);

    /**
     * 根据商品类型查找商品
     * @param kind
     * @return
     */
    public List<Good> queryGoodByKind(String kind);
}
