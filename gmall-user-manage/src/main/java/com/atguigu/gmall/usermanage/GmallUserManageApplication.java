package com.atguigu.gmall.usermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.gmall.usermanage.mapper")
public class GmallUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserManageApplication.class, args);
    }

}
