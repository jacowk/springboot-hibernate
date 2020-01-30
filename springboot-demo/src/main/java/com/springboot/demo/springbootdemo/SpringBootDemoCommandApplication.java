package com.springboot.demo.springbootdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class SpringBootDemoCommandApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoCommandApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world from CommandLineRunner");
    }

}
