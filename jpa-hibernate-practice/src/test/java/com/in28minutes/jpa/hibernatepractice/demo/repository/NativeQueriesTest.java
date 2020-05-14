package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernatepractice.demo.DemoApplication;
import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

@RunWith(SpringRunner.class) /* Creates a SpringBoot context */
@SpringBootTest(classes=DemoApplication.class)
public class NativeQueriesTest {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void nativeQueriesBasicTestCase() {
		List resultList = em.createNativeQuery("select * from course where is_deleted = 0", Course.class).getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	
	@Test
	public void nativeQueriesWithParameterTestCase() {
		Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = ? -> {}", resultList);
	}
	
	@Test
	public void nativeQueriesWithNamedParameterTestCase() {
		Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = :id -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void nativeQueriesToUpdateTestCase() {
		Query query = em.createNativeQuery("update course set last_updated_date = sysdate()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}
	
}
