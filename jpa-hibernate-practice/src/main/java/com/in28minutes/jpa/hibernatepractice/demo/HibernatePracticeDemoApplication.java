package com.in28minutes.jpa.hibernatepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Student;
import com.in28minutes.jpa.hibernatepractice.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernatepractice.demo.repository.StudentRepository;

/**
 * 
 * @author JACO57
 *
 * https://github.com/in28minutes/jpa-with-hibernate/tree/master/02-jpa-advanced
 * 
 * @OneToOne Relationship: Student - Passport
 * @OneToMany Relationship: Course - Review
 * @ManyToMany Relationship: Student - Course
 * 
 * mappedBy is always on the non-owning side
 * @ManyToOne and @OneToOne relationships always have a fetch type of eager (Ending with One)
 * @OneToMany and @ManyToMany relationships always have a fetch type of lazy (Ending with Many)
 * 
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
		
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hands-on Stuff."));
//		reviews.add(new Review("5", "Hatsoff."));
//		courseRepository.addReviewsForCourse(10003L, reviews);
		
		//studentRepository.insertHardcodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("Jack"), 
				new Course("Microservices in 100 Steps"));
	}

}
