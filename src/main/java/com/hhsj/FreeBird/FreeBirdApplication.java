package com.hhsj.FreeBird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@MapperScan("com.hhsj.FreeBird.dao")
@SpringBootApplication
public class FreeBirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeBirdApplication.class, args);
    }

}
