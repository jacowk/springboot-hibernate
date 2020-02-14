package com.in28minutes.jpa.hibernatepractice.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	/* mappedBy makes Student the owning side of the relationship, meaning that the passport id will be stored on 
	 * the student table in the database. Add mappedBy to the non-owning side of the relationship. */
	@OneToOne(fetch = FetchType.LAZY, mappedBy="passport")
	private Student student;
	
	public Passport() {
		super();
	}

	public Passport(String number) {
		super();
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
}
