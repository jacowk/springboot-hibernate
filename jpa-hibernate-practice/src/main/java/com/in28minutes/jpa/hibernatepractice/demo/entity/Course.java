package com.in28minutes.jpa.hibernatepractice.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Table(name = "CourseDetails")
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name = "fullname", nullable = false)
	private String name;
	
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

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}
	
}
