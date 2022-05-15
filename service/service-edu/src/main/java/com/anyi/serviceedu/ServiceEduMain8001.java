package com.anyi.serviceedu;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 安逸i
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(value = "com.anyi")
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
@EnableFeignClients
@EnableDiscoveryClient
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
public class ServiceEduMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain8001.class,args);
    }
}
