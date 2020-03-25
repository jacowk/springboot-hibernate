package com.in28minutes.jpa.hibernatepractice.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;

	public PartTimeEmployee() {
		super();
	}

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
	
}
