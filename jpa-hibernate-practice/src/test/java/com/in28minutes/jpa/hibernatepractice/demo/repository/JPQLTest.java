package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernatepractice.demo.DemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Student;

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
	
	@Test
	public void jpqlCoursesWithoutStudents() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c where c.students is empty", 
						Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	

	@Test
	public void jpqlCoursesWithAtLeast2Students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c where size(c.students) >= 2", 
						Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpqlCoursesOrderedByStudents() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c order by size(c.students) desc", 
						Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpqlStudentsWithPassportsInACertainPattern() {
		TypedQuery<Student> query = 
				em.createQuery("Select s from Student s where s.passport.number like '%1234%'", 
						Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	/*
	 * JPQL Supports:
	 * like 
	 * BETWEEN 100 AND 1000
	 * IS NULL
	 * upper, lower, trim, length
	 * 
	 * Types of joins
	 * JOIN => Select c, s from Course c JOIN c.students s
	 * LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
	 * CROSS JOIN => Select c, s from Course c, Student s
	 */
	
	@Test
	public void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList);
		for (Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void leftJoin() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList);
		for (Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void crossJoin() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList);
		for (Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
}
