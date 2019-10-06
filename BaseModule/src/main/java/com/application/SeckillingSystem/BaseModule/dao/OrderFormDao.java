package com.application.SeckillingSystem.BaseModule.dao;

import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/***
 * @author PG
 */
@Repository
public interface OrderFormDao extends JpaRepository<OrderForm, Long> {

    /**
     * 根据订单编号查询订单
     *
     * @param orderId
     * @return
     */
    @Query(value = "select * from OrderForm where order_id=?1 limit 1", nativeQuery = true)
    OrderForm queryDistinctByOrderId(String orderId);

    /**
     * 查询指定用户账号的所有订单，并且以倒序进行排序
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from OrderForm where user_id=?1 order by id desc ", nativeQuery = true)
    List<OrderForm> queryByUserId(String userId);




    /**
     * 根据用户和订单的状态查询订单
     *
     * @param user_id
     * @param status
     * @return
     */
    @Query(value = "select * from OrderForm where user_id=?1 and status=?2 order by id desc", nativeQuery = true)
    List<OrderForm> queryByUserAndStatus(String user_id, int status);

    @Transactional
    @Modifying
    @Query(value = "delete from order_form where order_id =?1")
    void deleteByOrderId(String orderId);


}
