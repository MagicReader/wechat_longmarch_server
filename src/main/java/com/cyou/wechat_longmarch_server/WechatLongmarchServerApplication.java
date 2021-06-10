package com.cyou.wechat_longmarch_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyou.wechat_longmarch_server.mapper")
public class WechatLongmarchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatLongmarchServerApplication.class, args);
    }

}
