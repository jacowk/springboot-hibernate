package com.in28minutes.jpa.hibernatepractice.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Review;
import com.in28minutes.jpa.hibernatepractice.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernatepractice.demo.repository.StudentRepository;

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
	
	@Autowired
	private StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HibernatePracticeDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		studentRepository.saveStudentWithPassport();
		
//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		
////		courseRepository.deleteById(10001L);
//		
//		courseRepository.save(new Course("Microservices in 100 Steps"));
//		courseRepository.playWithEntityManager();
		
//		courseRepository.addHardcodedReviewsForCourse();
		
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("5", "Great Hands-on Stuff."));
		reviews.add(new Review("5", "Hatsoff."));
		courseRepository.addReviewsForCourse(10003L, reviews);
	}

}
