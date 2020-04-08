package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class CriteriaQueryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpqlBasicTestCase() {
		//How do you write the following in java: Select c From Course c
		
		//Sequence of steps
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		//The root defines which tables you are getting the data from
		Root<Course> courseRoot = cq.from(Course.class); //Represents "from Course c"
		
		//3. Define Predicates etc using Criteria Builder
		
		
		//4. Add Predicates etc to the Criteria Builder
		
		
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void allCoursesHaving100Steps() {
		//"Select c from Course c where name like '%100 Steps'"
		
		//Sequence of steps
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		//The root defines which tables you are getting the data from
		Root<Course> courseRoot = cq.from(Course.class); //Represents "from Course c"
		
		//3. Define Predicates etc using Criteria Builder
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
		
		//4. Add Predicates etc to the Criteria Builder
		cq.where(like100Steps);
		
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query (like 100 Steps) -> {}", resultList);
		//Select c From Course c -> [Course [name=Web Services in 100 Steps], Course [name=Angular JS in 100 Steps - Updated], Course [name=JPA in 50 Steps], Course [name=Spring in 50 Steps], Course [name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void allCoursesWithoutStudents() {
		//"Select c from Course c where c.students is empty"
		
		//Sequence of steps
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		//The root defines which tables you are getting the data from
		Root<Course> courseRoot = cq.from(Course.class); //Represents "from Course c"
		
		//3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		//4. Add Predicates etc to the Criteria Builder
		cq.where(studentsIsEmpty);
		
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query (students is empts) -> {}", resultList);
	}
	
	@Test
	public void join() {
		//"Select c from Course c join c.students s"
		
		//Sequence of steps
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		//The root defines which tables you are getting the data from
		Root<Course> courseRoot = cq.from(Course.class); //Represents "from Course c"
		
		//3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		
		//4. Add Predicates etc to the Criteria Builder
		
		
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query (join) -> {}", resultList);
	}
	
	@Test
	public void leftJoin() {
		//"Select c from Course c left join c.students s"
		
		//Sequence of steps
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		//The root defines which tables you are getting the data from
		Root<Course> courseRoot = cq.from(Course.class); //Represents "from Course c"
		
		//3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		//4. Add Predicates etc to the Criteria Builder
		
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query (left join) -> {}", resultList);
	}
	
}
