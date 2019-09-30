package com.example.springbootbill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.springbootbill.mapper")
@SpringBootApplication
public class SpringBootBillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBillApplication.class, args);
    }

}
