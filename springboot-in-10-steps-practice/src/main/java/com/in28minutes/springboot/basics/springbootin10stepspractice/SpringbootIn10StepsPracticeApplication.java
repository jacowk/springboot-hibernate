package com.in28minutes.springboot.basics.springbootin10stepspractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootIn10StepsPracticeApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringbootIn10StepsPracticeApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(SpringbootIn10StepsPracticeApplication.class, args);
		for (String name: applicationContext.getBeanDefinitionNames())
		{
			System.out.println(name);
		}
	}

}
