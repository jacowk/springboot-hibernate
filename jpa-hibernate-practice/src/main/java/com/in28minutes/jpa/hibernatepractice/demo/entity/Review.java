package com.in28minutes.jpa.hibernatepractice.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/*
 * JPA Relationships
 * =================
 * 
 * Any relationship ending in One, is eager loading, e.g. @OneToOne, @ManyToOne
 * Any relationship ending in Many, is lazy loading, e.g. @OneToMany, @ManyToMany
 * 
 */
@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;
	private String description;
	
	@ManyToOne /* Default eager fetching */
	private Course course;
	
	public Review() {
		super();
	}

	public Review(ReviewRating rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}

}
