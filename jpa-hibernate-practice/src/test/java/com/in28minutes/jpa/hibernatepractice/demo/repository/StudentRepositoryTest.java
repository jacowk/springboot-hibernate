package com.in28minutes.jpa.hibernatepractice.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernatepractice.demo.HibernatePracticeDemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Passport;
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
@SpringBootTest(classes=HibernatePracticeDemoApplication.class)
public class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em; //Used to interact with the Persistence Context
	
	@Test
	public void someTest() {
		//In Hibernate, a Session = Persistence Context
		studentRepository.someOperationToUnderstandPersistenceContext();
	}
	
	/* 
	 * When you create a transaction, you also create a Persistence Context. The Persistence Context is a place
	 * where all entities that are being created are stored.
	 */
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("student -> {}", passport.getStudent());
		logger.info("passport -> {}", passport);
	}
	
}
