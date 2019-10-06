package com.application.seckilling.compute.service.Impl;

import com.application.SeckillingSystem.BaseModule.dao.OrderDetailDao;
import com.application.SeckillingSystem.BaseModule.entity.Good;
import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import com.application.SeckillingSystem.BaseModule.entity.OrderForm;
import com.application.seckilling.compute.redis.GoodRedisService;
import com.application.seckilling.compute.service.GoodService;
import com.application.SeckillingSystem.BaseModule.dao.OrderFormDao;
import com.application.SeckillingSystem.BaseModule.dao.GoodDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;

    @Resource
    private GoodRedisService goodRedisService;

    @Resource
    private OrderDetailDao orderDetailDao;

    private Logger logger = LoggerFactory.getLogger(GoodServiceImpl.class);

    /**
     * 购买商品 首先查询缓存查看余量是否充足，如果不足，订单状态失败，充足，生成订单，修改数据库以及缓存
     *
     * @param goodId
     * @param count
     * @return
     */
    @Override
    public synchronized boolean purchaseGood(String userId, String goodId, int count, OrderForm orderForm) {
        String key = "good_count:" + goodId;
        Integer num = 0;
        // 存在缓存
        if (goodRedisService.existKey(key)) {
            num = Integer.parseInt(goodRedisService.queryCache(key));
        }
        // 不存在缓存,意味着缓存过期了，需要重新进行导入
        else {
            num = goodDao.queryDistinctCountByGoodId(goodId);
            if (num == null) {
                num = 0;
            }
            goodRedisService.addCache(key, String.valueOf(num));
        }
        Good good = goodDao.queryDistinctByGoodId(goodId);

        if (num >= count) {
            // 更新缓存
            //logger.info("good count :"+num);
            goodRedisService.addCache(key, String.valueOf(num - count));
            // 更新数据库
            goodDao.editGood(goodId, num - count);
            // 生成订单，订单状态成功
            try {
                produceOrderForm(good, count, true, orderForm);
            } catch (Exception e) {
                logger.info("订单生成出现异常");
                return false;
            }
            return true;
        } else {
            // 生成订单，订单状态失败
            goodRedisService.addCache(key, String.valueOf(num));
            try {
                produceOrderForm(good, count, false, orderForm);
            } catch (Exception e) {
                logger.info("订单生成出现异常");
                return false;
            }
            return true;
        }
    }

    public void produceOrderForm(Good good, int count, boolean status, OrderForm orderForm) {
        OrderDetail detail = new OrderDetail();
        detail.setCount(count);
        detail.setGoodId(good.getGoodId());
        detail.setGoodName(good.getName());
        detail.setOrderStatus(status);
        detail.setPrice(good.getPrice());
        detail.setTotalValue(good.getPrice() * count);
        orderDetailDao.save(detail);
        Set<OrderDetail> details = new HashSet<>();
        details.add(detail);
        orderForm.setOrderDetails(details);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss a");
        orderForm.setTime(dateFormat.format(date));
        orderForm.setOrderStatus(status);
    }
}
