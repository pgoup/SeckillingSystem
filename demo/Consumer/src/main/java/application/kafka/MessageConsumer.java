package application.kafka;


import application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 抢购的消费者
 */
@Component
public class MessageConsumer
{
    @Autowired
    private GoodService goodService;

    private static ReentrantLock lock=new ReentrantLock();

    private static  int num=0;
    /**
     * 消费消息方法
     * 运用多线程的方法进行消息的消费
     * @param message
     * @return
     */
    @KafkaListener(topics = {"seckill"})
    public boolean consumeMessage(String message)
    {
        System.out.println("接收到的消息为："+message);
        String []array=message.split(",");
        System.out.print("转化后的参数为：");
        for (String a : array)
        {
            System.out.print(a+"  ");
        }
        int userId=Integer.parseInt(array[0]);
        String goodId=array[1];
        int count=Integer.parseInt(array[2]);
        //调用service，返回一个处理结束后的订单类


        Runnable task=new Runnable() {
            @Override
            public  void run() {

               if(  goodService.purchaseGood(goodId,count))
               {
                   //成功购买商品，构成订单信息

               }
               else
               {
                   //订单失败
               }
            }
        };

        lock.lock();
        num++;
        System.out.println("第"+num+"条消息被消费");
        task.run();
        lock.unlock();
        return true;

    }


}
