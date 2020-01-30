package com.in28minutes.jpa.hibernatepractice.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public Course save(Course course) {
		if (course.getId() == null) {
			//insert
			em.persist(course);
		}
		else {
			//update
			em.merge(course);
		}
		return course;
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
//	public void playWithEntityManager() {
//		Course course = new Course("Web Services in 100 Steps");
//		em.persist(course);
//		/* The method runs within a transaction, so this method is also persisted after the call to em.persist(course) */
//		course.setName("Web Services in 100 Steps - Updated"); 
//	}
	
//	public void playWithEntityManager() {
//		Course course1 = new Course("Web Services in 100 Steps");
//		em.persist(course1);
//		em.flush(); //Causes changes to be sent to the database
//		
//		course1.setName("Web Services in 100 Steps - Updated");
//		em.flush();
//		
//		Course course2 = new Course("Angular JS in 100 Steps");
//		em.persist(course2);
//		em.flush();
//		
//		em.detach(course2);
//		
//		course2.setName("Angular JS in 100 Steps - Updated");
//		em.flush();
//	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 100 Steps");
		em.persist(course2);
		
		em.flush();
		
//		em.detach(course1);
//		em.detach(course2);
		em.clear(); //Alternative to detach
		
		course1.setName("Web Services in 100 Steps - Updated");
		em.flush();
		
		course2.setName("Angular JS in 100 Steps - Updated");
		em.flush();
	}

}
