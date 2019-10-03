package application.dao.Impl;

import application.dao.OrderFormDao;
import application.entity.OrderForm;
import application.repository.OrderFormRepository;
import application.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderFormDaoImpl implements OrderFormDao {

  @Resource private OrderFormRepository orderFormRepository;
  /**
   * 添加订单
   *
   * @param orderForm
   * @return
   */
  @Override
  public void addOrderForm(OrderForm orderForm) {
    orderFormRepository.save(orderForm);
  }

  /**
   * 根据订单编号查询订单
   *
   * @param orderId
   * @return
   */
  @Override
  public OrderForm queryByOrderId(String orderId) {
    return orderFormRepository.queryByOrderId(orderId).get(0);
  }

  /**
   * 查询指定用户账号的所有订单，并且以倒序进行排序
   *
   * @param userId
   * @return
   */
  @Override
  public List<OrderForm> queryByUserId(String userId) {
    return orderFormRepository.queryByUserId(userId);
  }

  /**
   * 删除订单
   *
   * @param orderId
   * @return
   */
  @Override
  public boolean deleteOrderForm(String orderId) {
    if (orderFormRepository.deleteByOrderId(orderId) > 0) return true;
    else return false;
  }
}
