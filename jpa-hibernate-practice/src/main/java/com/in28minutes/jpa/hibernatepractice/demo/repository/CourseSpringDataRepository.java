package com.in28minutes.jpa.hibernatepractice.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.in28minutes.jpa.hibernatepractice.demo.entity.Course;

/* 
 * Turns this class into RESTful services. This class is now exposed as a Spring Data REST. 
 * Access the service with: http://localhost:8080/courses
 * Get the details of a course: http://localhost:8080/courses/10001
 */
@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> { /* Long refers to the "private Long id" property of Course */
	
	List<Course> findByNameAndId(String name, Long id);
	
	List<Course> findByName(String name);
	
	int countByName(String name);
	
	List<Course> findByNameOrderByIdDesc(String name);
	
	void deleteByName(String name);
	
	@Query("Select c From Course c where name like '%100 Steps'")
	List<Course> coursesWith100StepsInName(String name);
	
	@Query(value="Select * From Course c where name like '%100 Steps'", 
			nativeQuery=true)
	List<Course> coursesWith100StepsInNameUsingNativeQuery(String name);
	
	@Query(name="query_get_all_courses")
	List<Course> coursesWith100StepsInNameUsingNamedQuery();
	
}
