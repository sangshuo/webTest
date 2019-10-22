package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mappers")
public class VirtualJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualJavaApplication.class, args);
    }

}
