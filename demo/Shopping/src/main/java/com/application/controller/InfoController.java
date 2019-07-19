package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 查看商品信息等控制端
 */
@Controller
public class InfoController
{

    private static  int num=0;


    private static ReentrantLock lock=new ReentrantLock();
    @GetMapping("/test")
    @ResponseBody
    public String test()
    {
        lock.lock();
        num++;
        System.out.println("num:"+num);
        lock.unlock();
        return  null;
    }



    /**
     *获取所有的商品信息
     * @param
     * @return
     */
    @GetMapping("getAllInfo")
    public String getAllInfo()
    {
        return null;
    }


    /**
     * 根据商品id获取商品的具体信息
     * @param id
     * @return 具体页面
     */
    @PostMapping("getInfo")
    public String getInfo(int id)
    {
        return null;
    }




}
