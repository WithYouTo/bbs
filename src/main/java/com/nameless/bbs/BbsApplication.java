package com.nameless.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;


@SpringBootApplication
@MapperScan(basePackages = "com.nameless.*.mapper")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class, args);
    }

}
