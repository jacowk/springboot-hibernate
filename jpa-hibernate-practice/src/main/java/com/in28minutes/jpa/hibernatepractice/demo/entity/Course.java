package com.in28minutes.jpa.hibernatepractice.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name = "CourseDetails")
@NamedQueries(
		value = {
				@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
				@NamedQuery(name = "query_get_all_courses_join_fetch", query = "select c from Course c JOIN FETCH c.students"),
				@NamedQuery(name = "query_get_100_step_courses", query = "Select c From Course c where name like '%100 Steps'")}
		)
@Cacheable
@SQLDelete(sql="update course set is_deleted = true where id = ?")
@Where(clause="is_deleted = false")
public class Course {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	//@OneToMany(mappedBy="course", fetch=FetchType.EAGER)
	@OneToMany(mappedBy="course") /* The default fetch strategy is lazy. Prefer LAZY fetch strategy. */
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany (mappedBy="courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp
	@Column(name = "last_updated_date")
	private LocalDateTime lastUpatedDate;
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}
	
	public Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
	
}
