package com.springboot.demo.springbootdemo.controller;

import com.springboot.demo.springbootdemo.service.ProductService;
import com.springboot.demo.springbootdemo.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableScheduling
public class SpringbootRestDemoApplication {

    Logger logger = LoggerFactory.getLogger(SpringbootRestDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestDemoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProductService getProductService() {
        return new ProductServiceImpl();
    }

    //https://www.tutorialspoint.com/spring_boot/spring_boot_cors_support.htm
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/products").allowedOrigins("http://localhost:8080");
            }
        };
    }

}

/**
 * https://www.tutorialspoint.com/spring_boot/spring_boot_actuator.htm
 * /metrics 	To view the application metrics such as memory used, memory free, threads, classes, system uptime etc.
 * /env 	To view the list of Environment variables used in the application.
 * /beans 	To view the Spring beans and its types, scopes and dependency.
 * /health 	To view the application health
 * /info 	To view the information about the Spring Boot application.
 * /trace 	To view the list of Traces of your Rest endpoints.
 *
 * http://localhost:8080/actuator
 */