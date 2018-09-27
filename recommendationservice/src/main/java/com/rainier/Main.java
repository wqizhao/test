package com.rainier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Barcke on 2018/7/24.
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.rainier.dao"})
@EnableTransactionManagement
@EnableFeignClients
@EnableScheduling
@EnableRedisHttpSession

public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
