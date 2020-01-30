package com.in28minutes.jpa.hibernatepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;
import com.in28minutes.jpa.hibernatepractice.demo.repository.CourseRepository;

/**
 * 
 * @author JACO57
 *
 * https://github.com/in28minutes/jpa-with-hibernate/tree/master/02-jpa-advanced
 */
@SpringBootApplication
public class HibernatePracticeDemoApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HibernatePracticeDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		
////		courseRepository.deleteById(10001L);
//		
//		courseRepository.save(new Course("Microservices in 100 Steps"));
		courseRepository.playWithEntityManager();
	}

}
