package application.service.Impl;

import application.dao.OrderFormDao;
import application.entity.OrderForm;
import application.service.OrderFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderFromServiceImpl implements OrderFormService {

  private Logger logger = LoggerFactory.getLogger(OrderFromServiceImpl.class);
  @Resource private OrderFormDao orderFormDao;

  /**
   * 生成订单
   *
   * @param userId
   * @param goodId
   * @param count
   * @return
   */
  @Override
  public void addOrderForm(String userId, String goodId, int count, String status) {
    OrderForm orderForm = new OrderForm();
    orderForm.setOrderId("88563698");
    orderForm.setUserId(userId);
    orderForm.setGoodId(goodId);
    orderForm.setCount(count);
    orderForm.setStatus(status);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
    String time = dateFormat.format(new Date()).toString();
    orderForm.setTime(time);
    orderFormDao.addOrderForm(orderForm);
  }

  /**
   * 消费消息，
   *
   * @return
   */
  public boolean consumeMessage() {
    return false;
  }

  /**
   * 根据订单编号查找订单
   *
   * @param orderId
   * @return
   */
  @Override
  public OrderForm queryByOrderId(String orderId) {
    logger.info("进行查询订单操作。");
    return orderFormDao.queryByOrderId(orderId);
  }

  /**
   * 查找账号的所有订单
   *
   * @param userId
   * @return
   */
  @Override
  public List<OrderForm> queryByUserId(String userId) {
    logger.info("开始进行查找账号所有订单操作。");
    return orderFormDao.queryByUserId(userId);
  }

  /**
   * 删除订单
   *
   * @param orderId
   * @return
   */
  @Override
  public boolean deleteOrderFrom(String orderId) {
    logger.info("开始进行删除订单操作。");
    return orderFormDao.deleteOrderForm(orderId);
  }
}
