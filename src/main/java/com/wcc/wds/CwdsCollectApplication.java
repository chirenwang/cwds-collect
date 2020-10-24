package com.wcc.wds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.wcc.wds.web.mapper")
public class CwdsCollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwdsCollectApplication.class, args);
    }

}
