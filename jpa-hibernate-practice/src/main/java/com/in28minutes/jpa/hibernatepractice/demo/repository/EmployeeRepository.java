package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Employee;
import com.in28minutes.jpa.hibernatepractice.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernatepractice.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em; /* Make sure to read the code and comments in EntityManager */
	
	//insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	//retrieve all employees - Cannot be used with @MappedSuperclass annotation on Employee entity
//	public List<Employee> retrieveAllEmployees() {
//		return em.createQuery("select e from Employee e", Employee.class).getResultList();
//	}
	
	//Used with @MappedSuperclass annotation on Employee entity
	public List<PartTimeEmployee> retrievePartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	
	//Used with @MappedSuperclass annotation on Employee entity
	public List<FullTimeEmployee> retrieveFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
	
}
