package com.myth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author thyme
 * @ClassName SecurityMvcConfigurer
 * @Description 启动类
 * @Date
 */
@SpringBootApplication
@MapperScan("com.myth.*.dao")
@MapperScan("com.myth.*.mapper")
public class MythBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MythBootApplication.class, args);
    }
}
