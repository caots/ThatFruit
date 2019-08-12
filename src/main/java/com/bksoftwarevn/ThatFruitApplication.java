package com.bksoftwarevn;

import org.hibernate.cache.CacheException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

import java.util.Properties;

@SpringBootApplication
public class ThatFruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThatFruitApplication.class, args);
    }

}


