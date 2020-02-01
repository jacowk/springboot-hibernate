package com.springboot.demo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

    //https://www.tutorialspoint.com/spring_boot/spring_boot_file_handling.htm

    @RequestMapping(value = "/upload")
    public String fileUpload() {
        return "Not implemented";
    }

}
