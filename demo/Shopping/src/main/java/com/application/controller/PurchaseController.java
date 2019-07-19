package com.application.controller;

import com.application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 抢购商品的控制端
 */
@Controller
public class PurchaseController
{
    @Autowired
    private GoodService goodService;
    /**
     * 根据商品id即购买的数量进行商品的购买
     * 购买请求发送到本服务器，服务器将请求发送至消息队列中，完成请求的处理
     * 结果跳转至一个固定的页面，页面显示正在处理中，请稍后。。。。
     * 然后该页面就不断的请求查询是否抢购成功，长连接轮训
     * @param id
     * @param count
     * @return 返回到一个固定的页面
     */
    @GetMapping("/purchase")
    @ResponseBody
    public String purchase(@RequestParam("id") int id, @RequestParam("count") int count)
    {
        //消息发送成功
        if(goodService.purchaseGood(id, count))
        {
            return "测试成功";
        }
        //消息发送失败
        return null;
    }

}
