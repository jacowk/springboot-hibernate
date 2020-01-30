package com.springboot.demo.springbootdemo.controller;

import com.springboot.demo.springbootdemo.SpringbootDemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
public class ConsumeWebServiceController {

    Logger logger = LoggerFactory.getLogger(ConsumeWebServiceController.class);
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/template/products")
    public String getProductList() {
        logger.info("In getProductList");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
        String body = restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
        logger.info("Body, ", body);
        return body;
    }

}
