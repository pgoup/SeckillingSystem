package application.dao;

import application.entity.OrderForm;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderFormDao {

    /**
     * 添加订单
     * @param orderForm
     * @return
     */
    public void addOrderForm(OrderForm orderForm);

    /**
     * 根据订单编号查询订单
     * @param orderId
     * @return
     */
    public OrderForm queryByOrderId(String orderId);

    /**
     * 查询指定用户账号的所有订单，并且以倒序进行排序
     * @param userId
     * @return
     */
    public List<OrderForm> queryByUserId(String userId);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    public boolean deleteOrderForm(String orderId);
}
