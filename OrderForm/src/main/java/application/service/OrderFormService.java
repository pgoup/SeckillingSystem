package application.service;


import application.entity.OrderForm;

import java.util.List;

public interface OrderFormService
{

    /**
     * 用户下单，
     * @param userId
     * @param goodId
     * @param count
     * @return
     */
    public void addOrderForm(String userId,String goodId,int count,String status);


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
    public boolean deleteOrderFrom(String orderId);
}
