package application.service.Impl;

import application.dao.GoodDao;
import application.entity.Good;
import application.redis.GoodRedisService;
import application.service.GoodService;
import application.service.OrderFormService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.Oneway;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
  @Resource private GoodDao goodDao;

  @Resource private OrderFormService orderFormService;

  @Resource private GoodRedisService goodRedisService;

  private Logger logger = LoggerFactory.getLogger(GoodServiceImpl.class);
  /**
   * 购买商品 首先查询缓存查看余量是否充足，如果不足，订单状态失败，充足，生成订单，修改数据库以及缓存
   *
   * @param goodId
   * @param count
   * @return
   */
  @Override
  public synchronized boolean purchaseGood(String userId, String goodId, int count) {
    String key = "good_count:" + goodId;
    Good good;
    int num = 0;
    // 存在缓存
    if (goodRedisService.existKey(key)) {
      num = Integer.parseInt(goodRedisService.queryCache(key));
    }
    // 不存在缓存,意味着缓存过期了，需要重新进行导入
    else {
      List<Integer> nums=goodDao.queryCountByGoodId(goodId);
      if (nums.size()==0) {
       // logger.info("没有该商品编号");
        return false;
      }
      num=nums.get(0);
    }
    if (num >= count) {
      // 更新缓存
      //logger.info("good count :"+num);
      goodRedisService.addCache(key, String.valueOf(num-count));
      // 更新数据库
      goodDao.editGood(goodId, num - count);
      // 生成订单，订单状态成功
      //  orderFormService.addOrderForm(userId, goodId, count, "success");
      return true;
    } else {
      // 生成订单，订单状态失败
      //goodRedisService.addCache(key, String.valueOf(num));
      //  orderFormService.addOrderForm(userId, goodId, count, "failure");
      return false;
    }
  }
}
