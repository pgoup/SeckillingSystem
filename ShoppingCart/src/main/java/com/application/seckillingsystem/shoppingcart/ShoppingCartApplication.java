package com.application.seckillingsystem.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author PG
 */
@SpringBootApplication
@EnableEurekaClient
public class ShoppingCartApplication {
    public static  void main(String[]args){
        SpringApplication.run(ShoppingCartApplication.class,args);
    }

}
