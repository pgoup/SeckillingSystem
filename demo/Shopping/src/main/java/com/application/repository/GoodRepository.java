package com.application.repository;

import com.application.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodRepository extends JpaRepository<Good,Integer> {

    /**
     * 根据商品编号查询具体的一个商品
     * @param goodId
     * @return
     */
    @Query(value = "select * from good where good_id = ?1",nativeQuery = true)
    public List<Good> queryGoodByGoodId(String goodId);

    /**
     * 根据商品是否打折查询商品
     * @param discount
     * @return
     */
    @Query(value = "select * from good where discount=?1",nativeQuery = true)
    public List<Good> queryGoodByDiscount(int discount);

    /**
     * 根据商品类型查找商品
     * @param kind
     * @return
     */
    @Query(value = "select  * from  good where kind =?1",nativeQuery = true)
    public List<Good> queryGoodByKind(String kind);

}
