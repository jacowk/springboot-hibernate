package com.in28minutes.jpa.hibernatepractice.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
//@Table(name = "CourseDetails")
@NamedQueries(
		value = {@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
		@NamedQuery(name = "query_get_100_step_courses", query = "Select c From Course c where name like '%100 Steps'")}
		)
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	@UpdateTimestamp
	@Column(name = "last_updated_date")
	private LocalDateTime lastUpatedDate;
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate; 
	
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

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
	
}
