package com.springboot.demo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World";
    }

}
