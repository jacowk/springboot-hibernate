package com.in28minutes.jpa.hibernatepractice.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernatepractice.demo.DemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

/**
 * 
 * @author JACO57
 * 
 */
@RunWith(SpringRunner.class) /* Creates a SpringBoot context */
@SpringBootTest(classes=DemoApplication.class)
public class CourseSpringDataRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void findByIdCoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findByIdCourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithSpringDataRepository() {
//		Course course = new Course("Microservices in 10 Steps");
//		repository.save(course); //Create a Course
//		
//		course.setName("Microservices in 10 Steps - Updated");
//		repository.save(course); //Update a Course
		
		logger.info("Courses -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}
	
	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		logger.info("Sorted Courses -> {} ", repository.findAll(sort));
		logger.info("Count -> {} ", repository.count());
	}
	
	@Test
	public void pagination() {
		int page = 0;
		int size = 3;
		/* First page */
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {} ", firstPage.getContent());
		
		/* Second page */
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {} ", secondPage.getContent());
	}

	@Test
	public void findUsingName() {
		logger.info("FindByName -> {} ", repository.findByName("JPA in 50 Steps"));
	}
	
}
