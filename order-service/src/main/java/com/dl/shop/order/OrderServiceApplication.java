package com.dl.shop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.dl.base.configurer.FeignConfiguration;
import com.dl.base.configurer.RestTemplateConfig;
import com.dl.base.configurer.WebMvcConfigurer;
import com.dl.shop.order.configurer.Swagger2;
import com.dl.shop.order.core.ProjectConstant;

@SpringBootApplication
@Import({RestTemplateConfig.class, Swagger2.class, WebMvcConfigurer.class, FeignConfiguration.class})
@MapperScan(basePackages = { "com.dl.shop.order.dao", "com.dl.shop.order.dao2", "com.dl.shop.order.dao3" })
@EnableEurekaClient
@EnableFeignClients({"com.dl.member.api","com.dl.lottery.api", "com.dl.shop.payment.api"})
public class OrderServiceApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
