package com.smil;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
@EnableFeignClients
@EnableApolloConfig
@MapperScan(basePackages = {"com.smil.mn.infrastructure.mappers", "com.smil.dcs.mappers"})
public class MnServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MnServiceApplication.class, args);
    }

}
