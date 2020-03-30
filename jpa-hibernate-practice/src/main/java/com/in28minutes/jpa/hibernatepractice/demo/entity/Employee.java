package com.in28minutes.jpa.hibernatepractice.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/* ************************************************************************************************************************** *
/* Inheritance Types */
/* Single Table - Use this is performance is important */
//@Entity	
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
///* In the database, this column specifies which entity we are dealing with, FullTimePartTimeEmployee or PartTimeEmployee 
// * for SINGLE_TABLE and JOINED inheritance type. */
//@DiscriminatorColumn(name="EmployeeType")
/* ************************************************************************************************************************** *

/* Table Per Class: DiscriminatorColumn is not needed. */
//@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
/* ************************************************************************************************************************** *

/* Joined: DiscriminatorColumn is not needed. Use this is data integrity is of a concern. */
//@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
/* ************************************************************************************************************************** *

/* Mapped Super Class - Cannot be annotated with both @Entity and @MappedSuperclass */
@MappedSuperclass
public abstract class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	public Employee() {
		super();
	}

	public Employee(String name) {
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
		return String.format("Employee[%s]", name);
	}
	
}
