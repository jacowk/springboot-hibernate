package com.in28minutes.databasepractice.databasedemopractice.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.databasepractice.databasedemopractice.entity.Person;

/**
 * 
 * @author Jaco Koekemoer
 *	
 * Download H2 at http://www.h2database.com/html/download.html
 * 
 * https://www.baeldung.com/spring-boot-h2-database
 * https://www.baeldung.com/spring-boot-custom-auto-configuration
 * https://www.baeldung.com/jpa-insert
 * 
 * http://localhost:8080/h2console
 * jdbc:h2:mem:testdb
 *
 */

@Repository
@Transactional //Transaction Management
public class PersonJpaRepository {
	
	//Connect to the database
	@PersistenceContext
	private EntityManager entityManager; //Interface to the persistence context

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}
	
	public Person insert(Person person) {
		return entityManager.merge(person);	
	}
	
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);
	}
	
}
