package com.application.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.dao.GoodDao;
import com.application.entity.Good;
import com.application.service.GoodService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/** 查看商品信息等控制端 */
@Controller
public class InfoController {

  @Autowired private GoodService goodService;

  private Logger logger = LoggerFactory.getLogger(InfoController.class);

  @GetMapping("/test")
  @ResponseBody
  public String test() {

    return "dsfs";
  }

  @ResponseBody
  @GetMapping(value = "/queryByGoodId")
  public Good queryByGoodId(@PathParam("goodId") String goodId) {
    //logger.info("根据商品编号查询商品信息");
    return goodService.queryGoodByGoodId(goodId);
  }

  @ResponseBody
  @GetMapping("/queryByKind")
  public List<Good> queryByKind(@PathParam("kind") String kind) {
    logger.info("根据商品类型查询商品");
    return goodService.queryGoodByKind(kind);
  }

  @ResponseBody
  @GetMapping(value = "/queryByDiscount")
  public List<Good> queryByDiscount() {
    logger.info("查询打折的商品");
    return goodService.queryGoodByDiscount();
  }

  /**
   * 购买商品
   *
   * @param goodId
   * @param count
   * @return
   */
  @GetMapping("/purchaseGood")
  @ResponseBody
  public String purchaseGood(@PathParam("goodId") String goodId,@PathParam("count") String count) {
    goodService.purchaseGood(goodId, Integer.parseInt(count));
    return "购买订单已提交，可前往订单页面进行查看订单状态。";
  }
}
