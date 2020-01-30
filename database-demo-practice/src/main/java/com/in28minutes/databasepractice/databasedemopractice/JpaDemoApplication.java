package com.in28minutes.databasepractice.databasedemopractice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.databasepractice.databasedemopractice.entity.Person;
import com.in28minutes.databasepractice.databasedemopractice.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonJpaRepository personJpaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/* Insert stuff*/
		logger.info("Inserting 10001,  'Ranga', 'Hyderabad',sysdate()");
		personJpaRepository.insert(new Person(10001,  "Ranga", "Hyderabad", new Date()));
		logger.info("10002,  'James', 'New York',sysdate()");
		personJpaRepository.insert(new Person(10002,  "James", "New York", new Date()));
		logger.info("10003,  'Pieter', 'Amsterdam',sysdate()");
		personJpaRepository.insert(new Person(10003,  "Pieter", "Amsterdam", new Date()));
		
		/* Find by ID */
		logger.info("User id 1 -> {}", personJpaRepository.findById(1));
		
		/* Find all users */
		logger.info("All users -> {}", personJpaRepository.findAll());
		
		/* Update */
		logger.info("Updating 3 -> {}", personJpaRepository.update(new Person(3, "Pieter", "Utrecht", new Date())));
		
		/* Delete */
		logger.info("Deleting 2");
		personJpaRepository.deleteById(2);
		
		/* Find all users */
		logger.info("All users -> {}", personJpaRepository.findAll());
	}
	
}
