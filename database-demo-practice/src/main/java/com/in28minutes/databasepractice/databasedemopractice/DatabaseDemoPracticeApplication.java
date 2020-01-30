package com.in28minutes.databasepractice.databasedemopractice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.databasepractice.databasedemopractice.entity.Person;
import com.in28minutes.databasepractice.databasedemopractice.jdbc.PersonJdbcDao;

/**
 * 
 * @author Jaco Koekemoer
 *	
 * Download H2 at http://www.h2database.com/html/download.html
 * 
 * https://www.baeldung.com/spring-boot-h2-database
 * https://www.baeldung.com/spring-boot-custom-auto-configuration
 * 
 * http://localhost:8080/h2console
 * jdbc:h2:mem:testdb
 *
 */
//@SpringBootApplication
public class DatabaseDemoPracticeApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
	private PersonJdbcDao personJdbcDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoPracticeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("User id 10001 -> {}", personJdbcDao.findById(10001));
		logger.info("Deleting 10002 - No of rows deleted -> {}", personJdbcDao.deleteById(10002));
		logger.info("Inserting 10004 -> {}", personJdbcDao.insert(new Person(10004, "Tara", "Berlin", new Date())));
		logger.info("Updating 10003 -> {}", personJdbcDao.update(new Person(10003, "Pieter", "Utrecht", new Date())));
	}

}
