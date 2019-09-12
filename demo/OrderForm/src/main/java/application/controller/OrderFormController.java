package application.controller;

import application.dao.GoodDao;
import application.entity.OrderForm;
import application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/** 订单的控制端 */
@Controller
public class OrderFormController {

  @Resource private GoodDao goodDao;

  /**
   * 根据订单编号查询订单
   *
   * @param orderId
   * @return
   */
  @GetMapping(value = "/queryByOrderId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public OrderForm queryByOrderId(String orderId) {
    return null;
  }

  /**
   * 根据用户账号查询该用户的所有订单
   *
   * @param userId
   * @return
   */
  @GetMapping(value = "/queryByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public List<OrderForm> queryByUserId(String userId) {
    return null;
  }

  /**
   * 删除订单
   *
   * @param orderId
   * @return
   */
  @GetMapping(value = "/deleteOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public boolean deleteOrder(String orderId) {
    return true;
  }

  @GetMapping("/queryCount")
  @ResponseBody
  public int queryCountByGoodId(@PathParam("goodId") String goodId) {
    return goodDao.queryCountByGoodId(goodId).get(0);
  }
}
