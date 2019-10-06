package com.application.seckilling.shopping.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/** 商品抢购的消息发送者 */
@Component
@EnableKafka
public class MessageSender {

  @Autowired private KafkaTemplate<String, String> kafkaTemplate;
  // private static final MessageSender sender=new MessageSender();
  /**
   * kafka客户端发送消息
   * @param topic 主题
   * @param message 消息内容
   * @return*/

  public boolean sendMessage(String topic, String message) {
    try {
      //System.out.println("topic" + topic + "message" + message);
      kafkaTemplate.send(topic, message);
      kafkaTemplate.flush();

    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
