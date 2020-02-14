package com.in28minutes.jpa.hibernatepractice.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Passport;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em; /* Make sure to read the code and comments in EntityManager */
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if (student.getId() == null) {
			//insert
			em.persist(student);
		}
		else {
			//update
			em.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport); /* Without this you get a transient exception */
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context contains student
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context contains (student, passport)
		
		//Database Operation 3 - Update passport
		passport.setNumber("E123457");
		//Persistence Context contains (student, passport++)
		
		//Database Operation 4 - Update student
		student.setName("Ranga - updated");
		//Persistence Context contains (student++, passport++)
	}

}
