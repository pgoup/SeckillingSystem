package com.application.seckilling.shopping.service.serviceImpl;

import com.application.SeckillingSystem.BaseModule.dao.GoodDao;
import com.application.SeckillingSystem.BaseModule.entity.Good;
import com.application.seckilling.shopping.kafka.MessageSender;
import com.application.seckilling.shopping.redis.GoodRedisService;
import com.application.seckilling.shopping.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    private GoodDao goodDao;

    @Resource
    private GoodRedisService goodRedisService;

    @Resource
    private MessageSender sender;

    //private static volatile int num = 0;

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
        String address = "武汉市";
        String telephone = "12345678911";
        String userName = "张三";
        // 发送消息
        StringBuilder message = new StringBuilder();
        message.append(userId + ",");
        message.append(goodId + ",");
        message.append(count + ",");
        message.append(address + ",");
        message.append(telephone + ",");
        message.append(userName + ",");
        String topic = "seckill";
        //logger.info("开始进行购物消息的发送。");
        // num++;
        // System.out.println("发送消息次数：" + num);
        return sender.sendMessage(topic, message.toString());
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
        Good good = null;
        byte[] value;
        // 存在缓存直接获取
        if (goodRedisService.existKey(key.getBytes())) {
            // logger.info("缓存中获取数据");
            value = goodRedisService.queryCache(key.getBytes());
            Object object = SerializationUtils.deserialize(value);
            good = (Good) object;
        }
        // 不存在缓存，需要从数据库获取数据并添加至缓存
        else {
            // logger.info("数据库中获取数据");
            good = goodDao.queryDistinctByGoodId(goodId);
            value = SerializationUtils.serialize(good);
            goodRedisService.addCache(key.getBytes(), value);
        }
        return good;
    }

    /**
     * 根据商品是否打折查询商品
     *
     * @return
     */
    @Override
    public List<Good> queryGoodByDiscount() {
        String key = "discountIds";
        List<Good> goods;
        // 存在缓存，直接查询缓存内容
        if (goodRedisService.existKey(key.getBytes())) {
            logger.info("缓存中获取数据");
            // 缓存存储的是商品的id，需要在查询缓存获取具体商品类
            goods = convert(key.getBytes());
        }
        // 不存在缓存
        else {
            logger.info("数据库中获取数据");
            goods = goodDao.queryGoodByDiscount(1);
            goodRedisService.addCache(key.getBytes(), SerializationUtils.serialize(goods));
        }
        return goods;
    }

    /**
     * 根据商品类型查找商品
     *
     * @param kind
     * @return
     */
    @Override
    public List<Good> queryGoodByKind(String kind) {
        String key = "kind_" + kind;
        List<Good> goods;
        if (goodRedisService.existKey(key.getBytes())) {
            logger.info("缓存中获取数据");
            return convert(key.getBytes());
        } else {
            logger.info("数据库中获取数据");
            // 考虑是否为null
            goods = goodDao.queryGoodByKind(kind);
            byte[] value = SerializationUtils.serialize(goods);
            goodRedisService.addCache(key.getBytes(), value);
            return goods;
        }
    }

    public List<Good> convert(byte[] key) {
        List<Good> goods = null;
        byte[] value = goodRedisService.queryCache(key);
        Object object = SerializationUtils.deserialize(value);
        goods = (List<Good>) object;
        return goods;
    }
}
