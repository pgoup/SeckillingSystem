package com.application.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.application.dao.GoodDao;
import com.application.entity.Good;
import com.application.kafka.MessageSender;
import com.application.redis.GoodRedisService;
import com.application.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GoodServiceImpl implements GoodService {
  @Autowired private GoodDao goodDao;

  @Autowired private GoodRedisService goodRedisService;

  @Autowired private MessageSender sender;

  private static volatile int num = 0;

  private Logger logger = LoggerFactory.getLogger(GoodServiceImpl.class);

  /**
   * 用户id为userID的用户购买数量为count的商品
   *
   * @param goodId
   * @param count
   * @return
   */
  @Override
  public boolean purchaseGood(String goodId, int count) {
    // 获取顾客id
    Random random = new Random();
    int userId = random.nextInt(10000);
    // 发送消息
    String message =
        String.valueOf(userId) + "," + String.valueOf(goodId) + "," + String.valueOf(count);
    String topic = "seckill";
    //logger.info("开始进行购物消息的发送。");
   // num++;
   // System.out.println("发送消息次数：" + num);
    return sender.sendMessage(topic, message);
  }

  /**
   * 根据商品编号查询具体的一个商品
   *
   * @param goodId
   * @return
   */
  @Override
  public Good queryGoodByGoodId(String goodId) {
    String key = "goodId" + goodId;
    String value = "";
    // 存在缓存直接获取
    if (goodRedisService.existKey(key)) {
      // logger.info("缓存中获取数据");
      value = goodRedisService.queryCache(key);
      JSONObject object = JSONObject.parseObject(value);
      return JSONObject.toJavaObject(object, Good.class);
    }
    // 不存在缓存，需要从数据库获取数据并添加至缓存
    else {
      // logger.info("数据库中获取数据");
      Good good = goodDao.queryGoodByGoodId(goodId);
      value = JSONObject.toJSONString(good);
      goodRedisService.addCache(key, value);
      return good;
    }
  }

  /**
   * 根据商品是否打折查询商品
   *
   * @return
   */
  @Override
  public List<Good> queryGoodByDiscount() {
    String key = "discountIds";
    List<Good> goods = null;
    // 存在缓存，直接查询缓存内容
    if (goodRedisService.existKey(key)) {
      logger.info("缓存中获取数据");
      // 缓存存储的是商品的id，需要在查询缓存获取具体商品类
      String value = goodRedisService.queryCache(key);
      List<String> ids = JSONObject.parseArray(value, String.class);
      if (ids.size() == 0) return null;
      else {
        goods = new ArrayList<>();
        for (String id : ids) {
          goods.add(queryGoodByGoodId(id));
        }
        return goods;
      }
    }
    // 不存在缓存
    else {
      logger.info("数据库中获取数据");
      goods = goodDao.queryGoodByDiscount(1);
      List<String> ids = new ArrayList<>();
      for (Good good : goods) {
        ids.add(good.getGoodId());
      }
      String value = JSONObject.toJSONString(ids);
      goodRedisService.addCache(key, value);
      return goods;
    }
  }

  /**
   * 根据商品类型查找商品
   *
   * @param kind
   * @return
   */
  @Override
  public List<Good> queryGoodByKind(String kind) {
    String key = "kind" + kind;
    List<String> ids = new ArrayList<>();
    List<Good> goods = new ArrayList<>();
    if (goodRedisService.existKey(key)) {
      logger.info("缓存中获取数据");
      String value = goodRedisService.queryCache(key);
      ids = JSONObject.parseArray(value, String.class);
      for (String id : ids) {
        goods.add(queryGoodByGoodId(id));
      }
      return goods;
    } else {
      logger.info("数据库中获取数据");
      // 考虑是否为null
      goods = goodDao.queryGoodByKind(kind);
      String value = "";
      if (goods == null) {
        value = JSONObject.toJSONString(null);
      } else {
        for (Good good : goods) {
          ids.add(good.getGoodId());
        }
        value = JSONObject.toJSONString(ids);
      }
      goodRedisService.addCache(key, value);
      return goods;
    }
  }
}
