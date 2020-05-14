package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernatepractice.demo.DemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

/**
 * 
 * @author JACO57
 * 
 */
@RunWith(SpringRunner.class) /* Creates a SpringBoot context */
@SpringBootTest(classes=DemoApplication.class)
public class PerformanceTuningRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void creatingNPlusOneProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
		for (Course course: courses)
		{
			logger.info("Course -> {} Students -> {}", course, course.getStudents()); 
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		for (Course course: courses)
		{
			logger.info("Course -> {} Students -> {}", course, course.getStudents()); 
		}
	}
	

	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
				.getResultList();
		for (Course course: courses)
		{
			logger.info("Course -> {} Students -> {}", course, course.getStudents()); 
		}
	}
	
}
