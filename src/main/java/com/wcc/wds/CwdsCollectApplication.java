package com.wcc.wds;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wcc.wds.web.dao")
public class CwdsCollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwdsCollectApplication.class, args);
    }

}
