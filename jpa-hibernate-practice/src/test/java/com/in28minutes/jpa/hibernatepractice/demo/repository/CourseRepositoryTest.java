package com.in28minutes.jpa.hibernatepractice.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernatepractice.demo.DemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Review;

/**
 * 
 * @author JACO57
 * 
 * Sequence of events:
 * 1. Context is launched
 * 2. Test runs
 * 3. Context is destroyed
 *
 */
@RunWith(SpringRunner.class) /* Creates a SpringBoot context */
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findByIdBasicTestCase() {
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName()); /* Added a static import */
	}
	
	@Test
	@DirtiesContext /* After the test has run, Spring resets the data */
	public void deleteByIdTestCase() {
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
	}
	
	@Test
	@DirtiesContext /* After the test has run, Spring resets the data */
	public void saveTestCase() {
		//Get course
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
		
		//Update details
		course.setName("JPA in 50 Step - Updated");
		courseRepository.save(course);
		
		//Check the value
		Course course1 = courseRepository.findById(10001L);
		assertEquals("JPA in 50 Step - Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		courseRepository.playWithEntityManager();
	}
	
	@Test
	@Transactional /* Enables retrieval of lazy relationships */
	public void retrieveReviewsForCourse() {
		Course course = courseRepository.findById(10001L);
		logger.info("{}", course.getReviews()); /* If @Transactional is not defined, this line will throw an exception */
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse()); /* If @Transactional is not defined, this line will throw an exception */
	}
	
}
