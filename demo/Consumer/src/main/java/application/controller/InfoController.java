package application.controller;

import application.dao.GoodDao;
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
    @Autowired
    private GoodDao goodDao;

    private static ReentrantLock lock=new ReentrantLock();

    private static int num=0;
    @GetMapping("/testRun")
    @ResponseBody
    public String testRun()
    {
        Runnable task=new Runnable() {
            @Override
            public void run() {
                goodDao.editGood("2",1);
            }
        };
        lock.lock();
       task.run();

       num++;
       System.out.println("num:"+num);
       lock.unlock();

        return "测试完成";
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
