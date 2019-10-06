package com.application.seckilling.compute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
public class ComputeApplication extends SpringBootServletInitializer {

    private Logger logger = LoggerFactory.getLogger(ComputeApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("订单项目启动。");
        return builder.sources(ComputeApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ComputeApplication.class, args);
    }
}
