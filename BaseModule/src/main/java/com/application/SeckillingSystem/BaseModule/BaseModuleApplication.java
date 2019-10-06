package com.application.SeckillingSystem.BaseModule;

import com.application.SeckillingSystem.BaseModule.dao.GoodDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author PG
 */
@SpringBootApplication
@Controller
public class BaseModuleApplication extends SpringBootServletInitializer {

    @Resource
    private GoodDao goodDao;

    public static void main(String[] args) {
        SpringApplication.run(BaseModuleApplication.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    public String query() {
        int num=goodDao.editGood("8806320",50);
        int num2=goodDao.editGood("88063205",50);
        return ""+num+" "+num2;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
