package com.jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jpabook.jpashop")
public class JpaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaShopApplication.class, args);
    }

}
