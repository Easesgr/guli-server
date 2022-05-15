package com.anyi.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 安逸i
 * @version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.anyi")
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
@EnableDiscoveryClient
>>>>>>> 455a996 (整合springcloud)
>>>>>>> 7fddefb (整合springcloud)
public class VodServiceMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodServiceMain8003.class, args);
    }
}
