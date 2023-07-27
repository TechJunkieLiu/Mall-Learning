package com.aiyangniu.mall.enter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App2 {

    public static void main(String[] args) {
        System.setProperty("server.port", "0");
        SpringApplication.run(App.class, args);
    }
}
