package application.repository;

import application.entity.OrderForm;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface OrderFormRepository extends JpaRepository<OrderForm,Integer> {

    /**
     * 根据订单编号查询订单
     * @param orderId
     * @return
     */
    @Query(value = "select  * from orderform where  order_id=?1",nativeQuery = true)
    public List<OrderForm> queryByOrderId(String orderId);

    /**
     * 查询指定用户账号的所有订单，并且以倒序进行排序
     * @param userId
     * @return
     */
    @Query(value = "select  * from orderform where user_id=?1 order by id desc ",nativeQuery = true)
    public List<OrderForm> queryByUserId(String userId);

    /**
     * 根据订单编号删除订单
     * @param orderId
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "delete orderform from orderform where  order_id=?1",nativeQuery = true)
    public int deleteByOrderId(String orderId);
}
