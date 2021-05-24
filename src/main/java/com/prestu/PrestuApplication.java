package com.prestu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.prestu.mapper")
public class PrestuApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestuApplication.class, args);
    }

}
