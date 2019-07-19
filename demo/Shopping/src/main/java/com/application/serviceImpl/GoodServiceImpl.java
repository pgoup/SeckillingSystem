package com.application.serviceImpl;

import com.application.kafka.MessageSender;
import com.application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GoodServiceImpl implements GoodService
{

   // private static int num=0;
    @Autowired
    private MessageSender sender;
    /**
     *用户id为userID的用户购买数量为count的商品
     * @param goodId
     * @param count
     * @return
     */
    @Override
    public boolean purchaseGood( int goodId, int count) {
        //获取顾客id

        Random random=new Random();
        int userId=random.nextInt(10000);
        //发送消息
        String message=String.valueOf(userId)+","+ String.valueOf(goodId)+","+String.valueOf(count);
        String topic="seckill";
        System.out.println("消息主题为："+topic+"  消息内容为："+message);
        return sender.sendMessage(topic,message);

    }
}
