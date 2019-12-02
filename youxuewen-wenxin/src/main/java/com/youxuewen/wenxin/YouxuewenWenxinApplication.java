package com.youxuewen.wenxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.youxuewen.wenxin.dao")
@ComponentScan({"com.youxuewen.wenxin","org.n3r.idworker"})
public class YouxuewenWenxinApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouxuewenWenxinApplication.class, args);
    }

}
