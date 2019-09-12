package application.kafka;

import application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/** 抢购的消费者 */
@Component
public class MessageConsumer {
  @Resource private GoodService goodService;

  @Resource private Executor executor;
  private static ReentrantLock lock = new ReentrantLock();

  private static int num = 0;
  /**
   * 消费消息方法 运用多线程的方法进行消息的消费
   *
   * @param message
   * @return
   */
  @KafkaListener(topics = {"seckill"})
  public boolean consumeMessage(String message) {

    String[] array = message.split(",");
    String userId = array[0];
    String goodId = array[1];
    int count = Integer.parseInt(array[2]);
    // 调用service，返回一个处理结束后的订单类
    Runnable task =
        new Runnable() {
          @Override
          public void run() {
            goodService.purchaseGood(userId, goodId, count);
          }
        };
    lock.lock();
    executor.execute(task);
    lock.unlock();
    return true;
  }
}
