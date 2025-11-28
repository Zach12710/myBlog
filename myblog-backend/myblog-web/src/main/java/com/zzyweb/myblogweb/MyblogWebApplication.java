package com.zzyweb.myblogweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.zzyweb.*")
public class MyblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyblogWebApplication.class, args);
    }

}
