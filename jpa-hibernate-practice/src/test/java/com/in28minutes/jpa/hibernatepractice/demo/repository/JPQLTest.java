package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernatepractice.demo.HibernatePracticeDemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

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
@SpringBootTest(classes=HibernatePracticeDemoApplication.class)
public class JPQLTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpqlBasicTestCase() {
		List resultList = em.createQuery("Select c From Course c").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void jpqlNamedQueryTestCase() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Named query: query_get_all_courses -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void jpqlNamedQuery100StepTestCase() {
		List resultList = em.createNamedQuery("query_get_100_step_courses").getResultList();
		logger.info("Named query: query_get_100_step_courses -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void jpqlTypedTestCase() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void jpqlWhereTestCase() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c where name like '%100 Steps' -> {}", resultList);
		//Select c From Course c where name like '%100 Steps' -> [Course [name=Web Services in 100 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
}
