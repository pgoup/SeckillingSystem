package com.application.SeckillingSystem.BaseModule.dao;

import com.application.SeckillingSystem.BaseModule.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * description provide a common goodDao
 *
 * @author PG
 */
@Repository
public interface GoodDao extends JpaRepository<Good, Long> {



    /**
     * description  根据商品编号查找商品（编号唯一）
     *
     * @param goodId
     * @return
     */
    @Query(value = "select * from good where good_id=?1 limit 1", nativeQuery = true)
    Good queryDistinctByGoodId(String goodId);


    /**
     * 修改的时候直接进行查询，避免两次数据库操作
     *
     * @param goodId
     * @return
     */
    @Query(value = "select count from good where good_id=?1 limit 1", nativeQuery = true)
    Integer queryDistinctCountByGoodId(String goodId);

    /**
     * 根据商品是否打折查询商品
     *
     * @param discount
     * @return
     */
    @Query(value = "select * from good where discount=?1", nativeQuery = true)
    List<Good> queryGoodByDiscount(int discount);

    /**
     * 根据商品类型查找商品
     *
     * @param kind
     * @return
     */
    @Query(value = "select * from good where kind =?1",nativeQuery = true)
    List<Good> queryGoodByKind(String kind);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update good set count=(count-?2) where good_id=?1 and count>?2", nativeQuery = true)
    int editGood(String good_id, int count);


}
